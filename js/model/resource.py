import csv

with open('resource_urls.csv', 'rU') as csvfile:
	reader = csv.reader(csvfile, delimiter=',')
	for line in reader:
		if line[1] != 'null':
			print '{'
			print "    'name': '" + line[0] + "',"
			print "    'image': '',"
			print "    'link': '" + line[1] + "'"
			print '},'
