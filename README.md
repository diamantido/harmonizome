# Harmonizome

This repository contains the source code for the [Harmonizome](https://amp.pharm.mssm.edu/Harmonizome/).

## Funding

- [BD2K-LINCS Data Coordination and Integration Center](http://lincs-dcic.org/)
- [Illuminating the Druggable Genome, Knowledge Management Center](https://commonfund.nih.gov/idg/overview)

## Citation

Please acknowledge the Harmonizome in your publications by citing the following reference:

[Rouillard AD, Gundersen GW, Fernandez NF, Wang Z, Monteiro CD, McDermott MG, Ma'ayan A. The harmonizome: a collection of processed datasets gathered to serve and mine knowledge about genes and proteins. Database (Oxford). 2016 Jul 3;2016. pii: baw100.](http://database.oxfordjournals.org/content/2016/baw100.short)

## Configuration
Certain environment variables are necessary to connect to the database. Typically this can be handled with a `gradle.properties` file of the form:
```
DB_URL=jdbc:mysql://yourhost/yourdb
DB_USER=youruser
DB_PASS=yourpass
HARMONIZOME_PREFIX=Harmonizome
TOMCAT_PORT=8080
```

## Build instructions
Gradle is used to fetch dependencies, build and debug the project. The two commands below in different terminals can be used to automatically rebuild/deploy the project to an embedded tomcat server for a continuous development experience.
```
# Continuous build (rebuilds on file change)
gradle build -t

# Tomcat development server (reflects file changes)
gradle tomcatRun
```

### Docker
Gradle is used to assemble a war file to be deployed with a tomcat8-base docker image.
```
# Build and assemble war file
gradle war

# Construct docker image
docker build -t maayanlab/harmonizome:latest .
```

When you run the image you'll need to provide the environment variables:
```
docker run \
  -e DB_URL=jdbc:mysql://yourhost/yourdb \
  -e DB_USER=youruser \
  -e DB_PASS=yourpass \
  -p 8080:8080
  -it maayanlab/harmonizome:latest
```
