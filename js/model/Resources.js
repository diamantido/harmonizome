App.Model.Resources = Backbone.Model.extend({

    defaults: [
        {
            'name': 'Achilles',
            'image': "achilles.PNG",
            'link': 'http://www.broadinstitute.org/achilles'
        },
        {
            'name': 'Allen Brain Atlas',
            'image': "allenbrainatlas.PNG",
            'link': 'http://human.brain-map.org/'
        },
        {
            'name': 'Biocarta',
            'image': "biocarta.PNG",
            'link': 'http://www.biocarta.com/genes/index.asp'
        },
        {
            'name': 'BioGPS',
            'image': "biogps.PNG",
            'link': 'http://biogps.org/'
        },
        {
            'name': 'Biological General Repository of Interaction Datasets (BioGRID)',
            'image': "biogrid.PNG",
            'link': 'http://thebiogrid.org/'
        },
        {
            'name': 'Biomolecular Interaction Network Database (BIND)',
            'image': "bind.PNG",
            'link': 'http://bond.unleashedinformatics.com/'
        },
        {
            'name': 'Cancer Cell Line Encyclopedia (CCLE)',
            'image': "ccle.PNG",
            'link': 'http://www.broadinstitute.org/ccle/home'
        },
        {
            'name': 'Catalogue of Somatic Mutations In Cancer (COSMIC)',
            'image': "cosmic.PNG",
            'link': 'http://cancer.sanger.ac.uk/cancergenome/projects/cosmic/'
        },
        {
            'name': 'ChIP-X Enrichment Analysis (CHEA)',
            'image': "chea.PNG",
            'link': 'http://amp.pharm.mssm.edu/lib/chea.jsp'
        },
        {
            'name': 'ClinVar',
            'image': "clinvar.PNG",
            'link': 'http://www.ncbi.nlm.nih.gov/clinvar/'
        },
        {
            'name': 'Combined Pathways',
            'image': null,
            'link': 'null'
        },
        {
            'name': 'Comparative Toxicogenomics Database (CTD)',
            'image': "ctd.PNG",
            'link': 'http://ctdbase.org/'
        },
        {
            'name': 'COMPARTMENTS',
            'image': null,
            'link': 'http://compartments.jensenlab.org/Search'
        },
        {
            'name': 'Connectivity Map (CMAP)',
            'image': "cmap.PNG",
            'link': 'https://www.broadinstitute.org/cmap/'
        },
        {
            'name': 'CORUM',
            'image': null,
            'link': 'http://mips.helmholtz-muenchen.de/genre/proj/corum'
        },
        {
            'name': 'Database of Interacting Partners (DIP)',
            'image': "dip.PNG",
            'link': 'http://dip.doe-mbi.ucla.edu'
        },
        {
            'name': 'Dephosphorylation Database (DEPOD)',
            'image': "depod.PNG",
            'link': 'http://www.koehn.embl.de/depod/'
        },
        {
            'name': 'DISEASES',
            'image': null,
            'link': 'http://diseases.jensenlab.org/Search'
        },
        {
            'name': 'DrugBank',
            'image': "drugbank.PNG",
            'link': 'http://www.drugbank.ca/'
        },
        {
            'name': 'Encyclopedia of DNA Elements (ENCODE)',
            'image': "encode.PNG",
            'link': 'https://www.encodeproject.org/'
        },
        {
            'name': 'ESCAPE',
            'image': "escape.PNG",
            'link': 'http://www.maayanlab.net/ESCAPE/'
        },
        {
            'name': 'Gene Expression Omnibus (GEO)',
            'image': "geo.PNG",
            'link': 'http://www.ncbi.nlm.nih.gov/geo/'
        },
        {
            'name': 'Gene Ontology (GO)',
            'image': "go.PNG",
            'link': 'http://geneontology.org/'
        },
        {
            'name': 'Gene Reference Into Function (GeneRIF)',
            'image': null,
            'link': 'ftp://ftp.ncbi.nih.gov/gene/GeneRIF/'
        },
        {
            'name': 'GeneSigDB',
            'image': "genesigdb.PNG",
            'link': 'http://www.genesigdb.org'
        },
        {
            'name': 'Genetic Association Database (GAD)',
            'image': "gad.PNG",
            'link': 'http://geneticassociationdb.nih.gov/'
        },
        {
            'name': 'Genome Browser PWMs',
            'image': null,
            'link': 'null'
        },
        {
            'name': 'Genomics of Drug Sensitivity in Cancer (GDSC)',
            'image': "gdsc.PNG",
            'link': 'http://www.cancerrxgene.org/'
        },
        {
            'name': 'Genotype Tissue Expression (GTEx)',
            'image': "gtex.PNG",
            'link': 'http://www.gtexportal.org/home/'
        },
        {
            'name': 'Graph of Medicine',
            'image': null,
            'link': 'http://datadryad.org/resource/doi:10.5061/dryad.jp917'
        },
        {
            'name': 'Guide to Pharmacology',
            'image': "iuphar.PNG",
            'link': 'http://www.guidetopharmacology.org/'
        },
        {
            'name': 'GWAS Catalog',
            'image': "gwascatalog.PNG",
            'link': 'http://www.ebi.ac.uk/gwas/home'
        },
        {
            'name': 'GWASdb',
            'image': "gwasdb.PNG",
            'link': 'http://jjwanglab.org/gwasdb'
        },
        {
            'name': 'Heiser',
            'image': null,
            'link': 'http://www.ebi.ac.uk/arrayexpress/experiments/E-MTAB-181/'
        },
        {
            'name': 'Hub Proteins',
            'image': null,
            'link': 'null'
        },
        {
            'name': 'HuGE Navigator',
            'image': "hugenavigator.PNG",
            'link': 'http://www.hugenavigator.net/HuGENavigator/home.do'
        },
        {
            'name': 'Human Metabolome Database',
            'image': "humanmetabolome.PNG",
            'link': 'http://www.hmdb.ca/'
        },
        {
            'name': 'Human Phenotype Ontology (HPO)',
            'image': null,
            'link': 'http://www.human-phenotype-ontology.org/'
        },
        {
            'name': 'Human Protein Atlas (HPA)',
            'image': "humanproteinatlas.PNG",
            'link': 'http://www.proteinatlas.org/'
        },
        {
            'name': 'Human Protein Resource Database (HPRD)',
            'image': "hprd.PNG",
            'link': 'http://www.hprd.org'
        },
        {
            'name': 'Human Proteome Map (HPM)',
            'image': "humanproteomemap.PNG",
            'link': 'http://www.humanproteomemap.org/'
        },
        {
            'name': 'HumanCyc',
            'image': "humancyc.PNG",
            'link': 'http://humancyc.org/;http://www.pathwaycommons.org/about/'
        },
        {
            'name': 'Jaspar PWMs',
            'image': "jaspar.PNG",
            'link': 'http://jaspar.genereg.net/'
        },
        {
            'name': 'Kinase Enrichment Analysis (KEA)',
            'image': "kea.PNG",
            'link': 'http://amp.pharm.mssm.edu/lib/kea.jsp'
        },
        {
            'name': 'Kinativ',
            'image': null,
            'link': 'http://lincs.hms.harvard.edu/resources/assays/'
        },
        {
            'name': 'KinomeScan',
            'image': null,
            'link': 'http://lincs.hms.harvard.edu/data/kinomescan/'
        },
        {
            'name': 'Klijn',
            'image': null,
            'link': 'null'
        },
        {
            'name': 'Kyoto Encyclopedia of Genes and Genomes (KEGG)',
            'image': "kegg.PNG",
            'link': 'http://www.genome.jp/kegg/'
        },
        {
            'name': 'LINCS L1000 Connectivity Map (CMAP)',
            'image': "lincs.PNG",
            'link': 'http://www.lincscloud.org/l1000/'
        },
        {
            'name': 'LOCATE',
            'image': "locate.PNG",
            'link': 'http://locate.imb.uq.edu.au/'
        },
        {
            'name': 'Mammalian Phenotype Ontology (MPO)',
            'image': "mgi.PNG",
            'link': 'http://www.informatics.jax.org/phenotypes.shtml'
        },
        {
            'name': 'MiRTarBase',
            'image': "mirtarbase.PNG",
            'link': 'http://mirtarbase.mbc.nctu.edu.tw/'
        },
        {
            'name': 'Molecular Interaction Database (IntAct)',
            'image': "intact.PNG",
            'link': 'http://www.ebi.ac.uk/intact/'
        },
        {
            'name': 'Molecular Signatures Database (MSigDB)',
            'image': "msigdb.PNG",
            'link': 'http://www.broadinstitute.org/gsea/msigdb/index.jsp'
        },
        {
            'name': 'MotifMap',
            'image': null,
            'link': 'http://motifmap.ics.uci.edu/'
        },
        {
            'name': 'Nuclear Receptor Signaling Atlas (NURSA)',
            'image': "nursa.PNG",
            'link': 'http://www.nursa.org/nursa/index.jsf'
        },
        {
            'name': 'Online Mendelian Inheritance in Man (OMIM)',
            'image': "omim2.PNG",
            'link': 'http://www.omim.org/'
        },
        {
            'name': 'PANTHER',
            'image': "panther.PNG",
            'link': 'http://pantherdb.org/;http://www.pathwaycommons.org/about/'
        },
        {
            'name': 'Pathway Commons (PC)',
            'image': "pathwaycommons.PNG",
            'link': 'http://www.pathwaycommons.org/'
        },
        {
            'name': 'Pathway Interaction Database (PID)',
            'image': "ncipid.PNG",
            'link': 'http://pid.nci.nih.gov/'
        },
        {
            'name': 'Phosphosite Textmining',
            'image': null,
            'link': 'null'
        },
        {
            'name': 'PhosphoSitePlus',
            'image': "phosphositeplus.PNG",
            'link': 'http://www.phosphosite.org'
        },
        {
            'name': 'Proteomics Database (ProteomicsDB)',
            'image': "proteomicsdb.PNG",
            'link': 'https://www.proteomicsdb.org/'
        },
        {
            'name': 'Reactome',
            'image': "reactome.PNG",
            'link': 'http://www.reactome.org'
        },
        {
            'name': 'Recon X',
            'image': null,
            'link': 'http://humanmetabolism.org/'
        },
        {
            'name': 'Roadmap Epigenomics',
            'image': "roadmapepigenomics.PNG",
            'link': 'http://www.roadmapepigenomics.org/'
        },
        {
            'name': 'SILAC Phosphoproteomics',
            'image': null,
            'link': 'null'
        },
        {
            'name': 'SNPedia',
            'image': "snpedia.PNG",
            'link': 'http://snpedia.com/index.php/SNPedia'
        },
        {
            'name': 'STRING',
            'image': "string.PNG",
            'link': 'http://string-db.org/'
        },
        {
            'name': 'TargetScan microRNA',
            'image': "targetscan.PNG",
            'link': 'http://www.targetscan.org/'
        },
        {
            'name': 'The Cancer Genome Atlas (TCGA)',
            'image': "tcga.PNG",
            'link': 'https://tcga-data.nci.nih.gov/tcga/'
        },
        {
            'name': 'TISSUES',
            'image': null,
            'link': 'http://tissues.jensenlab.org/Search'
        },
        {
            'name': 'TRANSFAC',
            'image': null,
            'link': 'http://www.gene-regulation.com/'
        },
        {
            'name': 'Universal Protein Resource (Uniprot)',
            'image': "uniprot.PNG",
            'link': 'http://www.uniprot.org/'
        },
        {
            'name': 'Virus MINT',
            'image': "virusmint.PNG",
            'link': 'http://mint.bio.uniroma2.it/virusmint/'
        },
        {
            'name': 'Wikipathways',
            'image': "wikipathways.PNG",
            'link': 'http://www.wikipathways.org'
        }
    ]
});
