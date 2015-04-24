App.Model.Resources = Backbone.Model.extend({

    defaults: [
        {
            'name': 'Achilles',
            'image': 'achilles',
            'link': 'http://www.broadinstitute.org/achilles'
        },
        {
            'name': 'Allen Brain Atlas',
            'image': 'allenbrainatlas',
            'link': 'http://www.brain-map.org/'
        },
        {
            'name': 'Biocarta',
            'image': 'biocarta',
            'link': 'http://www.biocarta.com/genes/index.asp'
        },
        {
            'name': 'BioGPS',
            'image': '',
            'link': 'http://biogps.org/'
        },
        {
            'name': 'Biological General Repository of Interaction Datasets (BioGRID)',
            'image': 'biogrid',
            'link': 'http://thebiogrid.org/'
        },
        {
            'name': 'Biomolecular Interaction Network Database (BIND)',
            'image': 'bind',
            'link': 'http://bond.unleashedinformatics.com/'
        },
        {
            'name': 'Cancer Cell Line Encyclopedia (CCLE)',
            'image': 'ccle',
            'link': 'http://www.broadinstitute.org/ccle/home'
        },
        {
            'name': 'Catalogue of Somatic Mutations In Cancer (COSMIC)',
            'image': 'cosmic',
            'link': 'http://cancer.sanger.ac.uk/cancergenome/projects/cosmic/'
        },
        {
            'name': 'ChIP-X Enrichment Analysis (CHEA)',
            'image': 'chea',
            'link': 'http://amp.pharm.mssm.edu/lib/chea.jsp'
        },
        {
            'name': 'ClinVar',
            'image': 'clinvar',
            'link': 'http://www.ncbi.nlm.nih.gov/clinvar/'
        },
        {
            'name': 'Comparative Toxicogenomics Database (CTD)',
            'image': 'ctd',
            'link': 'http://ctdbase.org/'
        },
        {
            'name': 'COMPARTMENTS',
            'image': '',
            'link': 'http://compartments.jensenlab.org/Search'
        },
        {
            'name': 'Connectivity Map (CMAP)',
            'image': '',
            'link': 'https://www.broadinstitute.org/cmap/'
        },
        {
            'name': 'CORUM',
            'image': '',
            'link': 'http://mips.helmholtz-muenchen.de/genre/proj/corum'
        },
        {
            'name': 'Database of Interacting Partners (DIP)',
            'image': '',
            'link': 'http://dip.doe-mbi.ucla.edu'
        },
        {
            'name': 'Dephosphorylation Database (DEPOD)',
            'image': '',
            'link': 'http://www.koehn.embl.de/depod/'
        },
        {
            'name': 'DISEASES',
            'image': '',
            'link': 'http://diseases.jensenlab.org/Search'
        },
        {
            'name': 'DrugBank',
            'image': 'drugbank',
            'link': 'http://www.drugbank.ca/'
        },
        {
            'name': 'Encyclopedia of DNA Elements (ENCODE)',
            'image': 'encode',
            'link': 'https://www.encodeproject.org/'
        },
        {
            'name': '',
            'image': '',
            'link': 'http://www.maayanlab.net/ESCAPE/'
        },
        {
            'name': 'Gene Expression Omnibus (GEO)',
            'image': 'geo',
            'link': 'http://www.ncbi.nlm.nih.gov/geo/'
        },
        {
            'name': 'Gene Ontology (GO)',
            'image': 'go',
            'link': 'http://geneontology.org/'
        },
        {
            'name': 'Gene Reference Into Function (GeneRIF)',
            'image': '',
            'link': 'ftp://ftp.ncbi.nih.gov/gene/GeneRIF/'
        },
        {
            'name': 'GeneSigDB',
            'image': '',
            'link': 'http://www.genesigdb.org'
        },
        {
            'name': 'Genetic Association Database (GAD)',
            'image': '',
            'link': 'http://geneticassociationdb.nih.gov/'
        },
        {
            'name': 'Genomics of Drug Sensitivity in Cancer (GDSC)',
            'image': 'gdsc',
            'link': 'http://www.cancerrxgene.org/'
        },
        {
            'name': 'Genotype Tissue Expression (GTEx)',
            'image': 'gtex',
            'link': 'http://www.gtexportal.org/home/'
        },
        {
            'name': 'Graph of Medicine',
            'image': '',
            'link': 'http://datadryad.org/resource/doi:10.5061/dryad.jp917'
        },
        {
            'name': 'Guide to Pharmacology',
            'image': '',
            'link': 'http://www.guidetopharmacology.org/'
        },
        {
            'name': 'GWAS Catalog',
            'image': 'gwascatalog',
            'link': 'http://www.ebi.ac.uk/gwas/home'
        },
        {
            'name': 'GWASdb',
            'image': 'gwasdb',
            'link': 'http://jjwanglab.org/gwasdb'
        },
        {
            'name': 'Heiser',
            'image': '',
            'link': 'http://www.ebi.ac.uk/arrayexpress/experiments/E-MTAB-181/'
        },
        {
            'name': 'HuGE Navigator',
            'image': 'hugenavigator',
            'link': 'http://www.hugenavigator.net/HuGENavigator/home.do'
        },
        {
            'name': 'Human Metabolome Database',
            'image': '',
            'link': 'http://www.hmdb.ca/'
        },
        {
            'name': 'Human Phenotype Ontology (HPO)',
            'image': '',
            'link': 'http://www.human-phenotype-ontology.org/'
        },
        {
            'name': 'Human Protein Atlas (HPA)',
            'image': 'humanproteinatlas',
            'link': 'http://www.proteinatlas.org/'
        },
        {
            'name': 'Human Protein Resource Database (HPRD)',
            'image': '',
            'link': 'http://www.hprd.org'
        },
        {
            'name': 'Human Proteome Map (HPM)',
            'image': 'humanproteomemap',
            'link': 'http://www.humanproteomemap.org/'
        },
        {
            'name': 'HumanCyc',
            'image': 'humancyc',
            'link': 'http://www.humancyc.org'
        },
        {
            'name': 'Jaspar PWMs',
            'image': '',
            'link': 'http://jaspar.genereg.net/'
        },
        {
            'name': 'Kinase Enrichment Analysis (KEA)',
            'image': 'kea',
            'link': 'http://amp.pharm.mssm.edu/lib/kea.jsp'
        },
        {
            'name': 'Kinativ',
            'image': '',
            'link': 'http://lincs.hms.harvard.edu/resources/assays/'
        },
        {
            'name': 'KinomeScan',
            'image': '',
            'link': 'http://lincs.hms.harvard.edu/data/kinomescan/'
        },
        {
            'name': 'Kyoto Encyclopedia of Genes and Genomes (KEGG)',
            'image': 'kegg',
            'link': 'http://www.genome.jp/kegg/'
        },
        {
            'name': 'LINCS L1000 Connectivity Map (CMAP)',
            'image': '',
            'link': 'http://www.lincscloud.org/l1000/'
        },
        {
            'name': 'LOCATE',
            'image': 'locate',
            'link': 'http://locate.imb.uq.edu.au/'
        },
        {
            'name': 'Mammalian Phenotype Ontology (MPO)',
            'image': 'mgi',
            'link': 'http://www.informatics.jax.org/phenotypes.shtml'
        },
        {
            'name': 'MiRTarBase',
            'image': 'mirtarbase',
            'link': 'http://mirtarbase.mbc.nctu.edu.tw/'
        },
        {
            'name': 'Molecular Interaction Database (IntAct)',
            'image': 'intact',
            'link': 'http://www.ebi.ac.uk/intact/'
        },
        {
            'name': 'Molecular Signatures Database (MSigDB)',
            'image': '',
            'link': 'http://www.broadinstitute.org/gsea/msigdb/index.jsp'
        },
        {
            'name': 'MotifMap',
            'image': '',
            'link': 'http://motifmap.ics.uci.edu/'
        },
        {
            'name': 'Nuclear Receptor Signaling Atlas (NURSA)',
            'image': 'nursa',
            'link': 'http://www.nursa.org/nursa/index.jsf'
        },
        {
            'name': 'Online Mendelian Inheritance in Man (OMIM)',
            'image': 'omim',
            'link': 'http://www.omim.org/'
        },
        {
            'name': 'PANTHER',
            'image': 'panther',
            'link': 'http://www.pantherdb.org'
        },
        {
            'name': 'Pathway Commons (PC)',
            'image': 'pathwaycommons',
            'link': 'http://www.pathwaycommons.org/'
        },
        {
            'name': 'Pathway Interaction Database (PID)',
            'image': '',
            'link': 'http://pid.nci.nih.gov/'
        },
        {
            'name': 'PhosphoSitePlus',
            'image': 'phosphositeplus',
            'link': 'http://www.phosphosite.org'
        },
        {
            'name': 'Proteomics Database (ProteomicsDB)',
            'image': '',
            'link': 'https://www.proteomicsdb.org/'
        },
        {
            'name': 'Reactome',
            'image': 'reactome',
            'link': 'http://www.reactome.org'
        },
        {
            'name': 'Recon X',
            'image': '',
            'link': 'http://humanmetabolism.org/'
        },
        {
            'name': 'Roadmap Epigenomics',
            'image': 'roadmapepigenomics',
            'link': 'http://www.roadmapepigenomics.org/'
        },
        {
            'name': 'SNPedia',
            'image': 'snpedia',
            'link': 'http://snpedia.com/index.php/SNPedia'
        },
        {
            'name': 'TargetScan microRNA',
            'image': '',
            'link': 'http://www.targetscan.org/'
        },
        {
            'name': 'The Cancer Genome Atlas (TCGA)',
            'image': 'tcga',
            'link': 'https://tcga-data.nci.nih.gov/tcga/'
        },
        {
            'name': 'TISSUES',
            'image': '',
            'link': 'http://tissues.jensenlab.org/Search'
        },
        {
            'name': 'TRANSFAC',
            'image': '',
            'link': 'http://www.gene-regulation.com/'
        },
        {
            'name': 'Universal Protein Resource (Uniprot)',
            'image': 'uniprot',
            'link': 'http://www.uniprot.org/'
        },
        {
            'name': 'Virus MINT',
            'image': '',
            'link': 'http://mint.bio.uniroma2.it/virusmint/'
        },
        {
            'name': 'Wikipathways',
            'image': 'wikipathways',
            'link': 'http://www.wikipathways.org'
        }  
    ]
});
