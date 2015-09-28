"""A static class for reading and parsing data from the Harmonizome API.
"""


from enum import Enum
import json

# Support for both Python2.X and 3.X.
try:
    from urllib.request import urlopen
    from urllib.parse import quote_plus
except ImportError:
    from urllib2 import urlopen
    from urllib import quote_plus


class Entity(Enum):
    """The entity types supported by the Harmonizome API.
    """
    DATASET = 'dataset'
    GENE = 'gene'
    GENE_SET = 'gene_set'
    ATTRIBUTE = 'attribute'


class Harmonizome(object):

    BASE_URL = 'http://amp.pharm.mssm.edu/Harmonizome/api'
    VERSION = '1.0'

    def __init__(self, version):
        self.VERSION = version


    # Public API
    # ----------

    @classmethod
    def fetch(cls, entity, name=None, start_at=None):
        """Returns a single entity or a list, depending on if a name is
        provided. If no name is provided and start_at is specified, returns a
        list starting at that cursor position.
        """
        if name is not None:
            name = cls._encode(name)
            return cls._fetch_by_name(entity, name)
        if start_at is not None and type(start_at) is int:
            return cls._fetch_with_cursor(entity, start_at)

        url = '%s/%s/%s' % (cls.BASE_URL, cls.VERSION, entity.value)
        result = cls._open(url)
        return result

    @classmethod
    def fetch_next(cls, response):
        """Returns the next set of entities based on a previous API response.
        """
        start_at = cls._get_next(response)
        entity = cls._get_entity(response)
        return cls.fetch(entity=entity, start_at=start_at)


    # Utility functions
    # -----------------

    @classmethod
    def _fetch_with_cursor(cls, entity, start_at):
        """Returns a list of entities based on cursor position.
        """
        url = '%s/%s/%s?cursor=%s' % (
            cls.BASE_URL,
            cls.VERSION,
            entity.value,
            str(start_at)
        )
        result = cls._open(url)
        return result

    @classmethod
    def _fetch_by_name(cls, entity, name):
        """Returns a single entity based on name.
        """
        url = '%s/%s/%s/%s' % (
            cls.BASE_URL,
            cls.VERSION,
            entity.value,
            name
        )
        return cls._open(url)

    @classmethod
    def _open(cls, url):
        """Returns API response after decoding and loading JSON.
        """
        response = urlopen(url)
        data = response.read().decode('utf-8')
        return json.loads(data)

    @classmethod
    def _get_entity(cls, response):
        """Returns the entity from an API response.
        """
        path = response['next'].split('?')[0]
        return Entity(path.split('/')[3])

    @classmethod
    def _get_next(cls, response):
        """Returns the next property from an API response.
        """
        if response['next']:
            return int(response['next'].split('=')[1])
        return None

    @classmethod
    def _encode(cls, url):
        """URL encodes a string.
        """
        return quote_plus(url)


if __name__ == '__main__':
    response = Harmonizome.fetch(Entity.DATASET, name='PID pathways')
    print(response)
