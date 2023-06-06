# Harmonizome

This repository contains the source code for the [Harmonizome](https://amp.pharm.mssm.edu/Harmonizome/).

## Funding

- [BD2K-LINCS Data Coordination and Integration Center](http://lincs-dcic.org/)
- [Illuminating the Druggable Genome, Knowledge Management Center](https://commonfund.nih.gov/idg/overview)

## Citation

Please acknowledge the Harmonizome in your publications by citing the following reference:

[Rouillard AD, Gundersen GW, Fernandez NF, Wang Z, Monteiro CD, McDermott MG, Ma&#39;ayan A. The harmonizome: a collection of processed datasets gathered to serve and mine knowledge about genes and proteins. Database (Oxford). 2016 Jul 3;2016. pii: baw100.](http://database.oxfordjournals.org/content/2016/baw100.short)

## Configuration

Certain environment variables are necessary to connect to the database. Typically this can be handled with a `.env` file of the form:

```
DB_URL=jdbc:mysql://mariadb:3306/harmonizome
DB_USER=youruser
DB_PASS=yourpass
HARMONIZOME_VERSION=1.1.0
```

## Build instructions

### Docker

Depending on your system, you may need to adjust the Dockerfile to ensure that the Java installation directory in the Docker container is correct. Line 14 of the Dockerfile should be adjusted as follows:

```
# Windows
ENV PATH=$PATH:$GRADLE_HOME/bin JAVA_HOME=/usr/lib/jvm/java-11-openjdk-arm64

# Mac/Linux
ENV PATH=$PATH:$GRADLE_HOME/bin JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
```

Running these commands will construct and deploy two Docker containers: one runs a MariaDB image, and the other will launch an instance of Harmonizome which accesses the database stored in the MariaDB container's volume.

```
# Set up DB
docker build mariadb
docker compose up -d mariadb

# Set up Harmonizome application
docker build harmonizome
docker compose up -d harmonizome
```

After launching both containers, a local instance of Harmonizome will be available at localhost:8080/Harmonizome . All static pages will be functional, but a Harmonizome database dump file is required to display information about datasets, genes, and gene sets.
