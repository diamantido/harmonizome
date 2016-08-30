# Harmonizome

This repository contains the source code for the [Harmonizome](http://amp.pharm.mssm.edu/Harmonizome/).

### Funding

- [BD2K-LINCS Data Coordination and Integration Center](http://lincs-dcic.org/)
- [Illuminating the Druggable Genome, Knowledge Management Center](https://commonfund.nih.gov/idg/overview)

### Citation

Please acknowledge the Harmonizome in your publications by citing the following reference:

[Rouillard AD, Gundersen GW, Fernandez NF, Wang Z, Monteiro CD, McDermott MG, Ma'ayan A. The harmonizome: a collection of processed datasets gathered to serve and mine knowledge about genes and proteins. Database (Oxford). 2016 Jul 3;2016. pii: baw100.](http://database.oxfordjournals.org/content/2016/baw100.short)

### Ma'ayan Lab developers:

To use this project, you must have two files containing sensitive data:

`hibernate.properties`: This should have three lines:

```
hibernate.connection.username=<DB_USERNAME>
hibernate.connection.password=<DB_PASSWORD>
hibernate.connection.url=<DB_CONNECTION_STRING>
```

`email.properties`: This should have at least four lines:

```
email.username=harmonizome@gmail.com
email.password=<HARMONIZOME_GMAIL_PASSWORD>
email.avi=avi.maayan@mssm.edu
email.<CURRENT_DEVELOPER_NAME>=<CURRENT_DEVELOPER_EMAIL>
```