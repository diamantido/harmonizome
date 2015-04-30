import csv

with open('resource_logos_urls.csv', 'rU') as csvfile:
	reader = csv.reader(csvfile, delimiter=',')
	for line in reader:
		if line[2] != 'null':
			img = '"' + line[2] + '"'
		else:
			img = 'null'

		print '{'
		print "    'name': '" + line[0] + "',"
		print "    'image': " + img + ","
		print "    'link': '" + line[3] + "'"
		print '},'
