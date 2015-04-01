import json


with open('RESOURCE_META_DATA_20150331test.txt', 'r') as f:

	# format table headers to be used as keys
	headers = f.readline().rstrip().lower().replace(' ', '_').replace('-', '_').split('\t')

	# metadata is a list that will contain dicts, each dict contains a row of data
	metadata = []

	for line in f:

		# parse row of data
		entries = line.rstrip().split('\t')

		linedict = {}

		# construct dict for each row of data
		for i, entry in enumerate(entries):

			linedict[headers[i]] = entry

		metadata.append(linedict)


# for d in metadata:

	# print d


with open('resource_table_20150331_test.json', 'w') as f:

	json.dump(metadata, f, indent=2)


