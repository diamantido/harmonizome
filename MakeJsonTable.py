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

		linedict['publication'] = []

		# construct dict for each row of data
		for i, entry in enumerate(entries):

			if headers[i] == 'pmid':

				pmids = entry.split(';')

				for j, pmid in enumerate(pmids):

					linedict['publication'].append({})

					linedict['publication'][j]['pmid'] = pmid

			elif headers[i] == 'citation':

				citations = entry.split(';')

				for j, citation in enumerate(citations):

					linedict['publication'][j]['citation'] = citation

					author = citation.split(',')[0].split('(')[0].rstrip() + ' et al.'

					linedict['publication'][j]['author'] = author

			elif headers[i] == 'download_date':

				linedict[headers[i]] = {'year': entry[0:4], 'month': entry[4:6], 'day': entry[6:8]}

			else:

				linedict[headers[i]] = entry

		metadata.append(linedict)


# for d in metadata:

	# print d


with open('resource_table_test.json', 'w') as f:

	json.dump(metadata, f, indent=2)


