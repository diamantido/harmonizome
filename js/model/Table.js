App.Model.Table = Backbone.Model.extend({
    defaults: [
        {
            "gene_set_library": null, 
            "resource_description": "project screening essentiality of genes in cancer cell lines by measuring proliferation rates following gene knockdowns", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "20829823", 
                "citation": "CTD2 Network (2010) Towards patient-based cancer therapeutics. Nature Biotechnology. 28:904-906.", 
                "author": "CTD2 Network et al."
            }, 
            {
                "pmid": "21746896", 
                "citation": "Cheung, H.W. et al. (2011) Systematic investigation of genetic vulnerabilities across cancer cell lines reveals lineage-specific dependencies in ovarian cancer. PNAS. 108:12372-12377.", 
                "author": "Cheung et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "216", 
            "gene_list": null, 
            "data_url": "http://www.broadinstitute.org/achilles/datasets/5/download/Achilles_QC_v2.4.3.rnai.Gs.gct;http://www.broadinstitute.org/achilles/datasets/5/download/Achilles_v2.4_SampleInfo_small.txt", 
            "resource_name": "Achilles", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.broadinstitute.org/achilles/datasets/5/download/Achilles_Analysis_README_v2.4.3.txt", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by sensitivity of cell line to gene knockdown", 
            "number_of_genes": "4831", 
            "data_description": "fitness scores for cell lines following single gene knockdowns", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "achilles", 
            "resource_url": "http://www.broadinstitute.org/achilles"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of anatomic and gene expression data for the brain", 
            "edge_list": null, 
            "attribute": "tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "17151600", 
                "citation": "Lein, E. S. et al. (2007) Genome-wide atlas of gene expression in the adult mouse brain. Nature. 445:168-176.", 
                "author": "Lein et al."
            }, 
            {
                "pmid": "19826436", 
                "citation": "Jones, A. R., Overly, C. C. and S. M. Sunkin. (2009) The Allen Brain Atlas: 5 years and beyond. Nat Rev Neruosci. 10:821-828.", 
                "author": "Jones et al."
            }, 
            {
                "pmid": "23041053", 
                "citation": " Shen, E. H., Overly, C. C. and A. R. Jones. (2012) The Allen Human Brain Atlas: comprehensive gene expression mapping of the human brain. Trends Neurosci. 35:711-714.", 
                "author": " Shen et al."
            }, 
            {
                "pmid": "23193282", 
                "citation": "Sunkin, S. M., Ng, L., Lau, C., Dolbeare, T., Gilbert, T. L., Thompson, C. L., Hawrylycz, M. and C. Dang. (2013) Allen Brain Atlas: an integrated spatio-temporal portal for exploring the central nervous system.", 
                "author": "Sunkin et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "2232", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Allen Brain Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://help.brain-map.org/display/mousebrain/API;http://help.brain-map.org//display/api/Quantified+Data+by+Structures;http://help.brain-map.org/display/api/Allen+Brain+Atlas+API", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations by differential expression of gene across brain tissues", 
            "number_of_genes": "14248", 
            "data_description": "mRNA expression profiles for adult mouse brain tissues measured by in-situ hybridization spanning ~2000 anatomically defined brain structures", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the brain atlas api", 
            "directory": "brainatlasmouseinsitu", 
            "resource_url": "http://www.brain-map.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of anatomic and gene expression data for the brain", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "19826436", 
                "citation": "Jones, A. R., Overly, C. C. and S. M. Sunkin. (2009) The Allen Brain Atlas: 5 years and beyond. Nat Rev Neruosci. 10:821-828.", 
                "author": "Jones et al."
            }, 
            {
                "pmid": "23041053", 
                "citation": " Shen, E. H., Overly, C. C. and A. R. Jones. (2012) The Allen Human Brain Atlas: comprehensive gene expression mapping of the human brain. Trends Neurosci. 35:711-714.", 
                "author": " Shen et al."
            }, 
            {
                "pmid": "23193282", 
                "citation": "Sunkin, S. M., Ng, L., Lau, C., Dolbeare, T., Gilbert, T. L., Thompson, C. L., Hawrylycz, M. and C. Dang. (2013) Allen Brain Atlas: an integrated spatio-temporal portal for exploring the central nervous system.", 
                "author": "Sunkin et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.brainspan.org/api/v2/well_known_file_download/267666525", 
            "resource_name": "Allen Brain Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.brainspan.org/static/download.html;http://help.brain-map.org/download/attachments/3506181/Transcriptome_Profiling.pdf?version=1&modificationDate=1382036562736", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for human brain tissue samples measured by RNA-seq spanning 31 time points and 26 brain structures", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "brainatlashumanrnaseq", 
            "resource_url": "http://www.brainspan.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of anatomic and gene expression data for the brain", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "19826436", 
                "citation": "Jones, A. R., Overly, C. C. and S. M. Sunkin. (2009) The Allen Brain Atlas: 5 years and beyond. Nat Rev Neruosci. 10:821-828.", 
                "author": "Jones et al."
            }, 
            {
                "pmid": "23041053", 
                "citation": " Shen, E. H., Overly, C. C. and A. R. Jones. (2012) The Allen Human Brain Atlas: comprehensive gene expression mapping of the human brain. Trends Neurosci. 35:711-714.", 
                "author": " Shen et al."
            }, 
            {
                "pmid": "23193282", 
                "citation": "Sunkin, S. M., Ng, L., Lau, C., Dolbeare, T., Gilbert, T. L., Thompson, C. L., Hawrylycz, M. and C. Dang. (2013) Allen Brain Atlas: an integrated spatio-temporal portal for exploring the central nervous system.", 
                "author": "Sunkin et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.brainspan.org/api/v2/well_known_file_download/267666527", 
            "resource_name": "Allen Brain Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.brainspan.org/static/download.html;http://help.brain-map.org/download/attachments/3506181/Transcriptome_Profiling.pdf?version=1&modificationDate=1382036562736", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for human brain tissue samples measured by microarray spanning 27 time points and 26 brain structures", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "brainatlashumanmicroarray", 
            "resource_url": "http://www.brainspan.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of anatomic and gene expression data for the brain", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24695229", 
                "citation": "Miller, J. A. et al. (2014) Transcriptional landscape of the prenatal human brain. Nature. 508:199-206.", 
                "author": "Miller et al."
            }, 
            {
                "pmid": "19826436", 
                "citation": "Jones, A. R., Overly, C. C. and S. M. Sunkin. (2009) The Allen Brain Atlas: 5 years and beyond. Nat Rev Neruosci. 10:821-828.", 
                "author": "Jones et al."
            }, 
            {
                "pmid": "23041053", 
                "citation": " Shen, E. H., Overly, C. C. and A. R. Jones. (2012) The Allen Human Brain Atlas: comprehensive gene expression mapping of the human brain. Trends Neurosci. 35:711-714.", 
                "author": " Shen et al."
            }, 
            {
                "pmid": "23193282", 
                "citation": "Sunkin, S. M., Ng, L., Lau, C., Dolbeare, T., Gilbert, T. L., Thompson, C. L., Hawrylycz, M. and C. Dang. (2013) Allen Brain Atlas: an integrated spatio-temporal portal for exploring the central nervous system.", 
                "author": "Sunkin et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.brainspan.org/api/v2/well_known_file_download/278442900;http://www.brainspan.org/api/v2/well_known_file_download/278444085;http://www.brainspan.org/api/v2/well_known_file_download/278444090;http://www.brainspan.org/api/v2/well_known_file_download/278444094", 
            "resource_name": "Allen Brain Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.brainspan.org/static/download.html;http://help.brain-map.org/download/attachments/3506181/Prenatal_LMD_Microarray.pdf?version=1&modificationDate=1348698086255", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for 4 human prenatal brain tissue samples measured by microarray spanning 4 time points and ~300 brain structures", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "brainatlashumanprenatal", 
            "resource_url": "http://www.brainspan.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of anatomic and gene expression data for the brain", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22996553", 
                "citation": "Hawrylycz, M. J. et al. (2012) An anatomically comprehensive atlas of the adult human brain transcriptome. Nature. 489:391-399.", 
                "author": "Hawrylycz et al."
            }, 
            {
                "pmid": "19826436", 
                "citation": "Jones, A. R., Overly, C. C. and S. M. Sunkin. (2009) The Allen Brain Atlas: 5 years and beyond. Nat Rev Neruosci. 10:821-828.", 
                "author": "Jones et al."
            }, 
            {
                "pmid": "23041053", 
                "citation": " Shen, E. H., Overly, C. C. and A. R. Jones. (2012) The Allen Human Brain Atlas: comprehensive gene expression mapping of the human brain. Trends Neurosci. 35:711-714.", 
                "author": " Shen et al."
            }, 
            {
                "pmid": "23193282", 
                "citation": "Sunkin, S. M., Ng, L., Lau, C., Dolbeare, T., Gilbert, T. L., Thompson, C. L., Hawrylycz, M. and C. Dang. (2013) Allen Brain Atlas: an integrated spatio-temporal portal for exploring the central nervous system.", 
                "author": "Sunkin et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://human.brain-map.org/api/v2/well_known_file_download/178238387;http://human.brain-map.org/api/v2/well_known_file_download/178238373;http://human.brain-map.org/api/v2/well_known_file_download/178238359;http://human.brain-map.org/api/v2/well_known_file_download/178238316;http://human.brain-map.org/api/v2/well_known_file_download/178238266;http://human.brain-map.org/api/v2/well_known_file_download/178236545", 
            "resource_name": "Allen Brain Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://help.brain-map.org/display/humanbrain/Documentation", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for 6 adult human brain tissue samples measured by microarray spanning ~300 brain structures", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "brainatlashumandevelopmental", 
            "resource_url": "http://human.brain-map.org/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of curated pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "254", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "ftp://ftp1.nci.nih.gov/pub/PID/molList/100231.mol.tab.gz (example)", 
            "resource_name": "Biocarta", 
            "download_date": {
            "month": "12", 
            "day": "17", 
            "year": "2014"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "1397", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": "scrape html at http://pid.nci.nih.gov/browse_pathways.shtml#biocarta to get ID numbers for each pathway, then use the example Data URL template to download individual pathway files", 
            "directory": "biocarta", 
            "resource_url": "http://www.biocarta.com/genes/index.asp;http://pid.nci.nih.gov/browse_pathways.shtml#biocarta"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein-protein interactions", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23203989", 
                "citation": "Chatr-Aryamontri, A. et al. (2013) The BioGRID interaction database: 2013 update. Nucleic Acids Research. 41:D816-823.", 
                "author": "Chatr-Aryamontri et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://thebiogrid.org/downloads/archives/Release%20Archive/BIOGRID-3.3.122/BIOGRID-ORGANISM-3.3.122.mitab.zip", 
            "resource_name": "Biological General Repository of Interaction Datasets (BioGRID)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://thebiogrid.org/download.php", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from low-throughput or high-throughput protein-protein interaction data", 
            "number_of_genes": "0", 
            "data_description": "protein-protein interactions from low-throughput or high-throughput studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "biogrid", 
            "resource_url": "http://thebiogrid.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of biomolecular interactions", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "21233089", 
                "citation": "Isserlin, R., El-Badrawi, R. A. and G. D. Bader. (2011) The Biomolecular Interaction Network Database in PSI-MI 2.5. Database. 2011:baq037.", 
                "author": "Isserlin et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://download.baderlab.org/BINDTranslation/release1_0/PSIMI25_MITAB/taxid9606_PSIMI25.tsv;http://download.baderlab.org/BINDTranslation/release1_0/BINDTranslation_v1_mitab_TopSpecies.tar.gz", 
            "resource_name": "Biomolecular Interaction Network Database (BIND)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://download.baderlab.org/BINDTranslation/BIND_PSIMI25_README.txt", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from low-throughput or high-throughput protein-protein interaction data", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions from low-throughput or high-throughput studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "bind", 
            "resource_url": "http://bond.unleashedinformatics.com/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of gene expression, genotype, and drug sensitivity data for human cancer cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22460905", 
                "citation": "Barretina, J. et al. (2012) The Cancer Cell Line Encyclopedia enables predictive modelling of anticancer drug sensitivity. Nature. 483:603-607.", 
                "author": "Barretina et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "1037", 
            "gene_list": null, 
            "data_url": "http://www.broadinstitute.org/ccle/downloadFile/DefaultSystemRoot/exp_10/ds_21/CCLE_Expression_Entrez_2012-09-29.gct?downloadff=true&fileId=6763;http://www.broadinstitute.org/ccle/downloadFile/DefaultSystemRoot/exp_10/ds_22/CCLE_sample_info_file_2012-10-18.txt?downloadff=true&fileId=6801", 
            "resource_name": "Cancer Cell Line Encyclopedia (CCLE)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.broadinstitute.org/ccle/data/browseData?conversationPropagation=begin", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by differential expression of gene across cell lines", 
            "number_of_genes": "18025", 
            "data_description": "mRNA expression profiles for cancer cell lines measured by microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "cclemrna", 
            "resource_url": "http://www.broadinstitute.org/ccle/home"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of gene expression, genotype, and drug sensitivity data for human cancer cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22460905", 
                "citation": "Barretina, J. et al. (2012) The Cancer Cell Line Encyclopedia enables predictive modelling of anticancer drug sensitivity. Nature. 483:603-607.", 
                "author": "Barretina et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "1043", 
            "gene_list": null, 
            "data_url": "http://www.broadinstitute.org/ccle/downloadFile/DefaultSystemRoot/exp_10/ds_20/CCLE_copynumber_byGene_2013-12-03.txt.gz?downloadff=true&fileId=17599;http://www.broadinstitute.org/ccle/downloadFile/DefaultSystemRoot/exp_10/ds_22/CCLE_sample_info_file_2012-10-18.txt?downloadff=true&fileId=6801", 
            "resource_name": "Cancer Cell Line Encyclopedia (CCLE)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.broadinstitute.org/ccle/data/browseData?conversationPropagation=begin", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by copy number variation of gene across cell lines", 
            "number_of_genes": "23264", 
            "data_description": "gene-level copy number variation profiles for cancer cell lines measured by SNP microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "cclecnv", 
            "resource_url": "http://www.broadinstitute.org/ccle/home"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase of somatic mutations in cancer", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "20952405", 
                "citation": "Forbes, S. A. et al. (2011) COSMIC: mining complete cancer genomes in the Catalogue of Somatic Mutations in Cancer. Nucleic Acids Research. 39:D945-D950.", 
                "author": "Forbes et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "1026", 
            "gene_list": null, 
            "data_url": "http://cancer.sanger.ac.uk/files/cell_lines/current_release/CosmicCLP_MutantExport.tsv.gz", 
            "resource_name": "Catalogue of Somatic Mutations In Cancer (COSMIC)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://cancer.sanger.ac.uk/cancergenome/projects/cell_lines/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by mutation of gene in cell line", 
            "number_of_genes": "17972", 
            "data_description": "gene mutations in cancer cell lines from low-throughput or high-throughput studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "cosmicmut", 
            "resource_url": "http://cancer.sanger.ac.uk/cancergenome/projects/cosmic/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase of somatic mutations in cancer", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "20952405", 
                "citation": "Forbes, S. A. et al. (2011) COSMIC: mining complete cancer genomes in the Catalogue of Somatic Mutations in Cancer. Nucleic Acids Research. 39:D945-D950.", 
                "author": "Forbes et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "950", 
            "gene_list": null, 
            "data_url": "http://cancer.sanger.ac.uk/files/cell_lines/current_release/CosmicCLPCompleteCNA.tsv.gz", 
            "resource_name": "Catalogue of Somatic Mutations In Cancer (COSMIC)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://cancer.sanger.ac.uk/cancergenome/projects/cell_lines/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by copy number variation of gene across cell lines", 
            "number_of_genes": "19757", 
            "data_description": "gene-level copy number variation profiles for cancer cell lines measured by SNP microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "cosmiccnv", 
            "resource_url": "http://cancer.sanger.ac.uk/cancergenome/projects/cosmic/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "gene-set enrichment analysis tool testing for putative regulatory transcription factors of query gene-sets", 
            "edge_list": null, 
            "attribute": "transcription factor", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "20709693", 
                "citation": "Lachmann, A. et al. (2010) ChEA: transcription factor regulation inferred from integrating genome-wide ChIP-X experiments. Bioinformatics. 26:2438-2444.", 
                "author": "Lachmann et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "240", 
            "gene_list": null, 
            "data_url": "http://amp.pharm.mssm.edu/result/kea/chea-background.zip", 
            "resource_name": "ChIP-X Enrichment Analysis (CHEA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://amp.pharm.mssm.edu/lib/cheadownload.jsp", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-transcription factor associations by binding of transcription factor near transcription start site of gene", 
            "number_of_genes": "18928", 
            "data_description": "target genes of transcription factors from published ChIP-chip, ChIP-seq, and other transcription factor binding site profiling studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "chea", 
            "resource_url": "http://amp.pharm.mssm.edu/lib/chea.jsp"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of associations between genetic variants and clinically relevant phenotypes", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24234437", 
                "citation": "Landrum, M. J., Lee, J. M., Riley, G. R., Jang, W., Rubinstein, W. S., Church, D. M. and D. R. Maglott. (2014) ClinVar: public archive of relationships among sequence variation and human phenotype. Nucleic Acids Res. 42:D980:D985.", 
                "author": "Landrum et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/vcf_GRCh38/clinvar.vcf.gz", 
            "resource_name": "ClinVar", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "ftp://ftp.ncbi.nlm.nih.gov/pub/clinvar/README.txt;http://www.ncbi.nlm.nih.gov/variation/docs/human_variation_vcf/#clinvar", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by crowd-sourced curation", 
            "number_of_genes": "0", 
            "data_description": "SNP-phenotype associations curated by ClinVar users from various sources", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "clinvar", 
            "resource_url": "http://www.ncbi.nlm.nih.gov/clinvar/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "CMAP", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "cmap", 
            "resource_url": null
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of biological pathways aggregated from many source databases", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "3173", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": null, 
            "resource_name": "Combined Pathways", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "10688", 
            "data_description": "sets of proteins participating in pathways aggregated from the following pathway databases: Biocarta, HumanCyc, KEGG, NCI-Nature, Panther, Reactome, and Wikipathways", 
            "down_gene_set_library": null, 
            "notes": "pathways collected from the individual pathway databases also described in this document", 
            "directory": "combinedpathways", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of chemical-gene, chemical-disease, and gene-disease interactions manually curated from literature", 
            "edge_list": null, 
            "attribute": "chemical", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25326323", 
                "citation": "Davis, A. P., Grondin, C. J., Lennon-Hopkins, K., Saraceni-Richards, C., Sciaky, D., King, B. L., Wiegers, T. C. and C. J. Mattingly. (2015) The Comparative Toxicogenomics Database's 10th year anniversary: update 2015. Nucleic Acids Res. 43:D914-D920.", 
                "author": "Davis et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "small molecule", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://ctdbase.org/reports/CTD_chem_gene_ixns.tsv.gz", 
            "resource_name": "Comparative Toxicogenomics Database (CTD)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://ctdbase.org/downloads/;http://ctdbase.org/reports/CTD_chem_gene_ixn_types.tsv", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-chemical associations by manual literature curation", 
            "number_of_genes": "0", 
            "data_description": "chemical-gene interactions curated from literature", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "ctdchemical", 
            "resource_url": "http://ctdbase.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of chemical-gene, chemical-disease, and gene-disease interactions manually curated from literature", 
            "edge_list": null, 
            "attribute": "disease", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25326323", 
                "citation": "Davis, A. P., Grondin, C. J., Lennon-Hopkins, K., Saraceni-Richards, C., Sciaky, D., King, B. L., Wiegers, T. C. and C. J. Mattingly. (2015) The Comparative Toxicogenomics Database's 10th year anniversary: update 2015. Nucleic Acids Res. 43:D914-D920.", 
                "author": "Davis et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://ctdbase.org/reports/CTD_genes_diseases.tsv.gz", 
            "resource_name": "Comparative Toxicogenomics Database (CTD)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://ctdbase.org/downloads/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-disease associations by manual literature curation", 
            "number_of_genes": "0", 
            "data_description": "disease-gene interactions curated from literature", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "ctddisease", 
            "resource_url": "http://ctdbase.org/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein subcellular localization evidence collected by integrating experimental datasets, by integrating sequence-based predictions, by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "cellular compartment", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24573882", 
                "citation": "Binder, J. X., Pletscher-Frankild, S., Tsafou, K., Stolte, C., O'Donoghue, S. I., Schneider, R. and L. J. Jensen. (2014) COMPARTMENTS: unification and visualization of protein subcellular localization evidence. Database. 2014:bau012.", 
                "author": "Binder et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "2081", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_compartment_textmining_full.tsv", 
            "resource_name": "COMPARTMENTS", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://compartments.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-cellular compartment associations from automated text-mining of biomedical literature", 
            "number_of_genes": "14375", 
            "data_description": "gene-cellular compartment co-occurrence scores from text-mining biomedical abstracts", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensencompartmenttextmining", 
            "resource_url": "http://compartments.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein subcellular localization evidence collected by integrating experimental datasets, by integrating sequence-based predictions, by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "cellular compartment", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24573882", 
                "citation": "Binder, J. X., Pletscher-Frankild, S., Tsafou, K., Stolte, C., O'Donoghue, S. I., Schneider, R. and L. J. Jensen. (2014) COMPARTMENTS: unification and visualization of protein subcellular localization evidence. Database. 2014:bau012.", 
                "author": "Binder et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "1463", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_compartment_knowledge_full.tsv", 
            "resource_name": "COMPARTMENTS", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://compartments.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-cellular compartment associations by integrating evidence from manual literature curation", 
            "number_of_genes": "16738", 
            "data_description": "protein subcellular localization evidence scores by manual literature curation", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensencompartmentcurated", 
            "resource_url": "http://compartments.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein subcellular localization evidence collected by integrating experimental datasets, by integrating sequence-based predictions, by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "cellular compartment", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24573882", 
                "citation": "Binder, J. X., Pletscher-Frankild, S., Tsafou, K., Stolte, C., O'Donoghue, S. I., Schneider, R. and L. J. Jensen. (2014) COMPARTMENTS: unification and visualization of protein subcellular localization evidence. Database. 2014:bau012.", 
                "author": "Binder et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "59", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_compartment_experiments_full.tsv", 
            "resource_name": "COMPARTMENTS", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://compartments.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-cellular compartment associations by integrating evidence from experimental studies", 
            "number_of_genes": "6495", 
            "data_description": "protein subcellular localization evidence scores by integrating experimental data", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensencompartmentexpts", 
            "resource_url": "http://compartments.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "CORUM", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "corum", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein-protein interactions", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "14681454", 
                "citation": "Salwinski, L., Miller, C. S., Smith, A. J., Pettit, F. K., Bowie, J. U. and D. Eisenberg. (2004) The Database of Interacting Proteins: 2004 update. Nucleic Acids Res. 32:D499-D451.", 
                "author": "Salwinski et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://dip.doe-mbi.ucla.edu/dip/Download.cgi?SM=7&TX=9606", 
            "resource_name": "Database of Interacting Partners (DIP)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://dip.doe-mbi.ucla.edu/dip/Download.cgi", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from low-throughput or high-throughput protein-protein interaction data", 
            "number_of_genes": "0", 
            "data_description": "protein-protein interactions from low-throughput or high-throughput studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "dip", 
            "resource_url": "http://dip.doe-mbi.ucla.edu"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of evidence for disease-gene associations collected by integrating experimental datasets (GWAS), by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "disease", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "25484339", 
                "citation": "Pletscher-Frankild, S., Palleja, A., Tsafou, K., Binder, J. X. and L. J. Jensen. (2015) DISEASES: Text mining and data integration of disease-gene associations. Methods. 74:83-89.", 
                "author": "Pletscher-Frankild et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "4628", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_disease_textmining_full.tsv", 
            "resource_name": "DISEASES", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://diseases.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-disease associations from automated text-mining of biomedical literature", 
            "number_of_genes": "15309", 
            "data_description": "gene-disease co-occurrence scores from text-mining biomedical abstracts", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensendiseasetextmining", 
            "resource_url": "http://diseases.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of evidence for disease-gene associations collected by integrating experimental datasets (GWAS), by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "disease", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "25484339", 
                "citation": "Pletscher-Frankild, S., Palleja, A., Tsafou, K., Binder, J. X. and L. J. Jensen. (2015) DISEASES: Text mining and data integration of disease-gene associations. Methods. 74:83-89.", 
                "author": "Pletscher-Frankild et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "770", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_disease_knowledge_full.tsv", 
            "resource_name": "DISEASES", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://diseases.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-disease associations by integrating evidence from manual literature curation", 
            "number_of_genes": "2252", 
            "data_description": "disease gene evidence scores by manual literature curation", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensendiseasecurated", 
            "resource_url": "http://diseases.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of evidence for disease-gene associations collected by integrating experimental datasets (GWAS), by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "disease", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "25484339", 
                "citation": "Pletscher-Frankild, S., Palleja, A., Tsafou, K., Binder, J. X. and L. J. Jensen. (2015) DISEASES: Text mining and data integration of disease-gene associations. Methods. 74:83-89.", 
                "author": "Pletscher-Frankild et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "350", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_disease_experiments_full.tsv", 
            "resource_name": "DISEASES", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://diseases.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-disease associations by integrating evidence from experimental studies", 
            "number_of_genes": "4055", 
            "data_description": "disease gene evidence scores by integrating experimental data (GWAS)", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensendiseaseexpts", 
            "resource_url": "http://diseases.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase for drugs and their targets", 
            "edge_list": null, 
            "attribute": "drug", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "21059682", 
                "citation": "Knox, C., Law, V., Jewison, T., Liu, P., Ly, S., Frolkis, A., Pon, A., Banco, K., Mak, C., Neveu, V., Djoumbou, Y., Eisner, R., Guo, A. C. and D. S. Wishart. (2011) DrugBank 3.0: a comprehensive resource for 'omics' research on drugs. Nucleic Acids Research. 39:D1035-1041.", 
                "author": "Knox et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "small molecule", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.drugbank.ca/system/downloads/current/all_target_ids_all.csv.zip;http://www.drugbank.ca/system/downloads/current/all_enzyme_ids_all.csv.zip;http://www.drugbank.ca/system/downloads/current/all_carrier_ids_all.csv.zip;http://www.drugbank.ca/system/downloads/current/all_transporter_ids_all.csv.zip", 
            "resource_name": "DrugBank", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.drugbank.ca/downloads#protein-identifiers", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-drug associations by manual literature curation", 
            "number_of_genes": "0", 
            "data_description": "targets of drugs by manual literature curation", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "drugbank", 
            "resource_url": "http://www.drugbank.ca/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping genes and their regulatory elements on the human genome", 
            "edge_list": null, 
            "attribute": "transcription factor", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "15499007", 
                "citation": "ENCODE Consortium. (2004) The ENCODE (ENCyclopedia Of DNA Elements) Project. Science. 306:636-640.", 
                "author": "ENCODE Consortium. et al."
            }, 
            {
                "pmid": "21526222", 
                "citation": "ENCODE Consortium. (2011) A User's Guide to the Encyclopedia of DNA Elements (ENCODE). PLoS Biology. 9:e1001046.", 
                "author": "ENCODE Consortium. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "1259", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Encyclopedia of DNA Elements (ENCODE)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "https://www.encodeproject.org/help/getting-started/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-transcription factor associations by binding of transcription factor near transcription start site of gene", 
            "number_of_genes": "22308", 
            "data_description": "transcription factor binding site profiles for cell lines measured by ChIP-seq", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the encode api", 
            "directory": "encodetf", 
            "resource_url": "https://www.encodeproject.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping genes and their regulatory elements on the human genome", 
            "edge_list": null, 
            "attribute": "cell line sample (sample defined by cell line and transcription factor)", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "15499007", 
                "citation": "ENCODE Consortium. (2004) The ENCODE (ENCyclopedia Of DNA Elements) Project. Science. 306:636-640.", 
                "author": "ENCODE Consortium. et al."
            }, 
            {
                "pmid": "21526222", 
                "citation": "ENCODE Consortium. (2011) A User's Guide to the Encyclopedia of DNA Elements (ENCODE). PLoS Biology. 9:e1001046.", 
                "author": "ENCODE Consortium. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "1259", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Encyclopedia of DNA Elements (ENCODE)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "https://www.encodeproject.org/help/getting-started/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line sample associations by differential binding of transcription factor to gene across cell lines", 
            "number_of_genes": "22308", 
            "data_description": "transcription factor binding site profiles for cell lines measured by ChIP-seq", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the encode api", 
            "directory": "encodetfbycellline", 
            "resource_url": "https://www.encodeproject.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping genes and their regulatory elements on the human genome", 
            "edge_list": null, 
            "attribute": "cell line sample (sample defined by cell line and histone modification)", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "15499007", 
                "citation": "ENCODE Consortium. (2004) The ENCODE (ENCyclopedia Of DNA Elements) Project. Science. 306:636-640.", 
                "author": "ENCODE Consortium. et al."
            }, 
            {
                "pmid": "21526222", 
                "citation": "ENCODE Consortium. (2011) A User's Guide to the Encyclopedia of DNA Elements (ENCODE). PLoS Biology. 9:e1001046.", 
                "author": "ENCODE Consortium. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Encyclopedia of DNA Elements (ENCODE)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "https://www.encodeproject.org/help/getting-started/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line sample associations by differential modification of histones near gene across cell line samples", 
            "number_of_genes": "0", 
            "data_description": "histone modification profiles for cell lines measured by ChIP-seq", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the encode api", 
            "directory": "encodehmbycellline", 
            "resource_url": "https://www.encodeproject.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "ESCAPE", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "escape", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of functional genomic data obtained by microarray or next generation sequencing from published studies", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23193258", 
                "citation": "Barrett, T. et al. (2013) NCBI GEO: archive for functional genomics data sets--update. Nucleic Acids Research. 41:D991-995.", 
                "author": "Barrett et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Gene Expression Omnibus (GEO)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations by differential expression of gene A following perturbation of gene B", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for cell lines or tissues following genetic perturbation (knockdown, knockout, over-expression, mutation) measured by microarray", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the geo api.  First, accession numbers for datasets and for control and experimental group samples must be obtained by manually searching the geo database.  Then data can be downloaded using the api.", 
            "directory": "geogene", 
            "resource_url": "http://www.ncbi.nlm.nih.gov/geo/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of functional genomic data obtained by microarray or next generation sequencing from published studies", 
            "edge_list": null, 
            "attribute": "chemical", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23193258", 
                "citation": "Barrett, T. et al. (2013) NCBI GEO: archive for functional genomics data sets--update. Nucleic Acids Research. 41:D991-995.", 
                "author": "Barrett et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "small molecule", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Gene Expression Omnibus (GEO)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-chemical associations by differential expression of gene following chemical perturbation", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for cell lines or tissues following chemical perturbation measured by microarray", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the geo api.  First, accession numbers for datasets and for control and experimental group samples must be obtained by manually searching the geo database.  Then data can be downloaded using the api.", 
            "directory": "geochemical", 
            "resource_url": "http://www.ncbi.nlm.nih.gov/geo/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of functional genomic data obtained by microarray or next generation sequencing from published studies", 
            "edge_list": null, 
            "attribute": "disease", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23193258", 
                "citation": "Barrett, T. et al. (2013) NCBI GEO: archive for functional genomics data sets--update. Nucleic Acids Research. 41:D991-995.", 
                "author": "Barrett et al."
            }, 
            {
                "pmid": "19756046", 
                "citation": " Dudley, J. T., Tibshirani, R., Deshpande, T. and A. J. Butte. (2009) Disease signatures are robust across tissues and experiments. Mol Syst Biol. 5:307.", 
                "author": " Dudley et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Gene Expression Omnibus (GEO)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-disease associations by differential expression of gene following disease perturbation", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for cell lines or tissues following disease perturbation measured by microarray", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the geo api.  Accession numbers for datasets and for control and experimental group samples were provided by the Dudley Lab.", 
            "directory": "geodisease", 
            "resource_url": "http://www.ncbi.nlm.nih.gov/geo/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of functional genomic data obtained by microarray or next generation sequencing from published studies", 
            "edge_list": null, 
            "attribute": "virus", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23193258", 
                "citation": "Barrett, T. et al. (2013) NCBI GEO: archive for functional genomics data sets--update. Nucleic Acids Research. 41:D991-995.", 
                "author": "Barrett et al."
            }, 
            {
                "pmid": "19756046", 
                "citation": " Dudley, J. T., Tibshirani, R., Deshpande, T. and A. J. Butte. (2009) Disease signatures are robust across tissues and experiments. Mol Syst Biol. 5:307.", 
                "author": " Dudley et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "pathogen", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Gene Expression Omnibus (GEO)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-disease associations by differential expression of gene following virus perturbation", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for cell lines or tissues following virus perturbation measured by microarray", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the geo api.  Accession numbers for datasets and for control and experimental group samples were provided by the Dudley Lab.", 
            "directory": "geovirus", 
            "resource_url": "http://www.ncbi.nlm.nih.gov/geo/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of genes annotated with biological terms (molecular functions, cellular components, or biological processes) selected from a structured vocabulary", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "biological process (BP)", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "23161678", 
                "citation": "Gene Ontology Consortium. (2013) Gene Ontology annotations and resources. Nucleic Acids Research. 41:D530-535.", 
                "author": "Gene Ontology Consortium. et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "13212", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://geneontology.org/gene-associations/gene_association.goa_ref_human.gz;http://purl.obolibrary.org/obo/go/go-basic.obo", 
            "resource_name": "Gene Ontology (GO)", 
            "download_date": {
            "month": "03", 
            "day": "31", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://geneontology.org/gene-associations/readme/goa_ref_human.README", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-biological process (BP) associations from curated gene annotations", 
            "number_of_genes": "15717", 
            "data_description": "curated annotations of genes with biological processes (BP)", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gobp", 
            "resource_url": "http://geneontology.org/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of genes annotated with biological terms (molecular functions, cellular components, or biological processes) selected from a structured vocabulary", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "molecular function (MF)", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "23161678", 
                "citation": "Gene Ontology Consortium. (2013) Gene Ontology annotations and resources. Nucleic Acids Research. 41:D530-535.", 
                "author": "Gene Ontology Consortium. et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "4162", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://geneontology.org/gene-associations/gene_association.goa_ref_human.gz;http://purl.obolibrary.org/obo/go/go-basic.obo", 
            "resource_name": "Gene Ontology (GO)", 
            "download_date": {
            "month": "03", 
            "day": "31", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://geneontology.org/gene-associations/readme/goa_ref_human.README", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-molecular function (MF) associations from curated gene annotations", 
            "number_of_genes": "15777", 
            "data_description": "curated annotations of genes with molecular functions (MF)", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gomf", 
            "resource_url": "http://geneontology.org/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of genes annotated with biological terms (molecular functions, cellular components, or biological processes) selected from a structured vocabulary", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "cellular component (CC)", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "23161678", 
                "citation": "Gene Ontology Consortium. (2013) Gene Ontology annotations and resources. Nucleic Acids Research. 41:D530-535.", 
                "author": "Gene Ontology Consortium. et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "1547", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://geneontology.org/gene-associations/gene_association.goa_ref_human.gz;http://purl.obolibrary.org/obo/go/go-basic.obo", 
            "resource_name": "Gene Ontology (GO)", 
            "download_date": {
            "month": "03", 
            "day": "31", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://geneontology.org/gene-associations/readme/goa_ref_human.README", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-cellular component (CC) associations from curated gene annotations", 
            "number_of_genes": "16757", 
            "data_description": "curated annotations of genes with cellular components (CC)", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gocc", 
            "resource_url": "http://geneontology.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of genes annotated with short statements describing gene functions asserted by publications", 
            "edge_list": null, 
            "attribute": "biological term", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "14728215", 
                "citation": "Mitchell, J. A. et al. (2003) Gene indexing: characterization and analysis of NLM's GeneRIFs. AMIA Annu Symp Proc. 2003:460-464.", 
                "author": "Mitchell et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "ftp://ftp.ncbi.nih.gov/gene/GeneRIF/generifs_basic.gz", 
            "resource_name": "Gene Reference Into Function (GeneRIF)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "ftp://ftp.ncbi.nih.gov/gene/README", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-biological term associations from literature-supported statements describing functions of genes", 
            "number_of_genes": "0", 
            "data_description": "statements describing functions of genes distilled from biomedical publications", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "generif", 
            "resource_url": "ftp://ftp.ncbi.nih.gov/gene/GeneRIF/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "GeneSigDB", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "genesigdb", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of genetic association data and metadata from published studies reorganized into a standard format", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "15118671", 
                "citation": "Becker, K. G., Barnes, K. C., Bright, T. J. and S. A. Wang. (2004) The genetic association database. Nat Genet. 36:431-432.", 
                "author": "Becker et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://geneticassociationdb.nih.gov/data.zip", 
            "resource_name": "Genetic Association Database (GAD)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations curated from genetic association studies", 
            "number_of_genes": "0", 
            "data_description": "positive and negative gene-phenotype associations curated from genetic association studies", 
            "down_gene_set_library": null, 
            "notes": "must separate negative associations from positive associations", 
            "directory": "gad", 
            "resource_url": "http://geneticassociationdb.nih.gov/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Genome Browser PWMs", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "genomebrowser", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of gene expression, genotype, and drug sensitivity data for human cancer cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22460902", 
                "citation": "Garnett, M. J. et al. (2012) Systematic identification of genomic markers of drug sensitivity in cancer cells. Nature. 483:570-575.", 
                "author": "Garnett et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "624", 
            "gene_list": null, 
            "data_url": "ftp://ftp.sanger.ac.uk/pub/project/cancerrxgene/releases/release-5.0/expU133A.txt.zip;ftp://ftp.sanger.ac.uk/pub/project/cancerrxgene/releases/release-5.0/gdsc_cell_lines_w5.csv", 
            "resource_name": "Genomics of Drug Sensitivity in Cancer (GDSC)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.cancerrxgene.org/downloads/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by differential expression of gene across cell lines", 
            "number_of_genes": "11704", 
            "data_description": "mRNA expression profiles for cancer cell lines measured by microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gdsc", 
            "resource_url": "http://www.cancerrxgene.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping expression quantitative train loci on the human genome", 
            "edge_list": null, 
            "attribute": "SNP", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23715323", 
                "citation": "GTEx Consortium. (2013) The Genotype-Tissue Expression (GTEx) project. Nature Genetics. 45:580-585.", 
                "author": "GTEx Consortium. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "genomic feature", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "javascript:portalClient.browseDatasets.downloadFile('Multi_tissue_eQTL_GTEx_Pilot_Phase_datasets.tar','http://www.gtexportal.org/static/datasets/gtex_analysis_pilot_data_2013_01_31/multi_tissue_eqtls/Multi_tissue_eQTL_GTEx_Pilot_Phase_datasets.tar');javascript:portalClient.browseDatasets.downloadFile('GTEx_genot_imputed_variants_info4_maf05_CR95_CHR_POSb37_ID_REF_ALT.txt.zip','http://www.gtexportal.org/static/datasets/gtex_analysis_2014_01_17/reference/GTEx_genot_imputed_variants_info4_maf05_CR95_CHR_POSb37_ID_REF_ALT.txt.zip')", 
            "resource_name": "Genotype Tissue Expression (GTEx)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "javascript:portalClient.browseDatasets.downloadFile('README','http://www.gtexportal.org/static/datasets/gtex_analysis_pilot_data_2013_01_31/multi_tissue_eqtls/README')", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-SNP associations by likelihood that SNP regulates gene", 
            "number_of_genes": "0", 
            "data_description": "significance values for all gene-SNP pairs testing likelihood that SNP affects gene expression", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gtexeqtl", 
            "resource_url": "http://www.gtexportal.org/home/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping expression quantitative train loci on the human genome", 
            "edge_list": null, 
            "attribute": "tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23715323", 
                "citation": "GTEx Consortium. (2013) The Genotype-Tissue Expression (GTEx) project. Nature Genetics. 45:580-585.", 
                "author": "GTEx Consortium. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "javascript:portalClient.browseDatasets.downloadFile('GTEx_Analysis_2014-01-17_RNA-seq_RNA-SeQCv1.1.8_gene_rpkm.gct.gz','http://www.gtexportal.org/static/datasets/gtex_analysis_2014_01_17/rna_seq_data/GTEx_Analysis_2014-01-17_RNA-seq_RNA-SeQCv1.1.8_gene_rpkm.gct.gz');javascript:portalClient.browseDatasets.downloadFile('GTEx_Data_2014-01-17_Annotations_SampleAttributesDS.txt','http://www.gtexportal.org/static/datasets/gtex_analysis_2014_01_17/annotations/GTEx_Data_2014-01-17_Annotations_SampleAttributesDS.txt')", 
            "resource_name": "Genotype Tissue Expression (GTEx)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.gtexportal.org/home/datasets2", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations by differential expression of gene across tissues", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for tissues measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gtextissue", 
            "resource_url": "http://www.gtexportal.org/home/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping expression quantitative train loci on the human genome", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23715323", 
                "citation": "GTEx Consortium. (2013) The Genotype-Tissue Expression (GTEx) project. Nature Genetics. 45:580-585.", 
                "author": "GTEx Consortium. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "javascript:portalClient.browseDatasets.downloadFile('GTEx_Analysis_2014-01-17_RNA-seq_RNA-SeQCv1.1.8_gene_rpkm.gct.gz','http://www.gtexportal.org/static/datasets/gtex_analysis_2014_01_17/rna_seq_data/GTEx_Analysis_2014-01-17_RNA-seq_RNA-SeQCv1.1.8_gene_rpkm.gct.gz');javascript:portalClient.browseDatasets.downloadFile('GTEx_Data_2014-01-17_Annotations_SampleAttributesDS.txt','http://www.gtexportal.org/static/datasets/gtex_analysis_2014_01_17/annotations/GTEx_Data_2014-01-17_Annotations_SampleAttributesDS.txt')", 
            "resource_name": "Genotype Tissue Expression (GTEx)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.gtexportal.org/home/datasets2", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for tissue samples measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gtexsample", 
            "resource_url": "http://www.gtexportal.org/home/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "dataset of co-occurrence frequencies for 1 million medical terms (drugs, diseases, operations, proteins, etc.) extracted from 20 million clinical notes", 
            "edge_list": null, 
            "attribute": "clinical term", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": "Finlayson, S. G., LePendu, P. and N. H. Shah. (2014) Building the graph of medicine from millions of clinical narratives. Scientific Data. 1:140032.", 
                "author": "Finlayson et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://datadryad.org/bitstream/handle/10255/dryad.68981/1_Cofrequency_Counts.tar.gz?sequence=1", 
            "resource_name": "Graph of Medicine", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://datadryad.org/bitstream/handle/10255/dryad.68981/README.txt?sequence=2", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-clinical term associations by co-occurrence frequency in electronic health records", 
            "number_of_genes": "0", 
            "data_description": "co-occurrence frequencies for pairs of medical terms in electronic health records", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "graphofmedicine", 
            "resource_url": "http://datadryad.org/resource/doi:10.5061/dryad.jp917"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase for drug targets and their ligands", 
            "edge_list": null, 
            "attribute": "ligand (chemical)", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24234439", 
                "citation": "Pawson, A. J., Sharman, J. L., Benson, H. E., Faccenda, E., Alexander, S. P., Buneman, O. P., Davenport, A. P., McGrath, J. C., Peters, J. A., Southan, C., Spedding, M., Yu, W., Harmar, A. J., and NC-IUPHAR. (2014) The IUPHAR/BPS Guide to PHARMACOLOGY: an expert-driven knowledgebase of drug targets and their ligands. Nucleic Acids Res. 42:1098-1106.", 
                "author": "Pawson et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "small molecule", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.guidetopharmacology.org/DATA/interactions.csv", 
            "resource_name": "Guide to Pharmacology", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.guidetopharmacology.org/DATA/file_descriptions.txt", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "receptor-ligand (chemical) associations by expert curation", 
            "number_of_genes": "0", 
            "data_description": "ligand-receptor interactions curated by experts", 
            "down_gene_set_library": null, 
            "notes": "file includes small molecule ligands, need to filter for proteins", 
            "directory": "guidetopharmchemical", 
            "resource_url": "http://www.guidetopharmacology.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase for drug targets and their ligands", 
            "edge_list": null, 
            "attribute": "ligand (protein)", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24234439", 
                "citation": "Pawson, A. J., Sharman, J. L., Benson, H. E., Faccenda, E., Alexander, S. P., Buneman, O. P., Davenport, A. P., McGrath, J. C., Peters, J. A., Southan, C., Spedding, M., Yu, W., Harmar, A. J., and NC-IUPHAR. (2014) The IUPHAR/BPS Guide to PHARMACOLOGY: an expert-driven knowledgebase of drug targets and their ligands. Nucleic Acids Res. 42:1098-1106.", 
                "author": "Pawson et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.guidetopharmacology.org/DATA/interactions.csv", 
            "resource_name": "Guide to Pharmacology", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.guidetopharmacology.org/DATA/file_descriptions.txt", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "receptor-ligand (protein) associations by expert curation", 
            "number_of_genes": "0", 
            "data_description": "ligand-receptor interactions curated by experts", 
            "down_gene_set_library": null, 
            "notes": "file includes small molecule ligands, need to filter for proteins", 
            "directory": "guidetopharmprotein", 
            "resource_url": "http://www.guidetopharmacology.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of significant SNP-phenotype associations manually curated from published GWA Studies", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24316577", 
                "citation": "Welter, D., MacArthur, J., Morales, J., Burdett, T., Hall, P., Junkins, H., Klemm, A., Flicek, P., Manolio, T., Hindorff, L. and H. Parkinson. (2014) The NHGRI GWAS Catalog, a curated resource of SNP-trait associations. Nucleic Acids Res. 42:D1001-D1006.", 
                "author": "Welter et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.ebi.ac.uk/gwas/api/search/downloads/full", 
            "resource_name": "GWAS Catalog", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.ebi.ac.uk/gwas/docs/fileheaders", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by manual GWAS curation", 
            "number_of_genes": "0", 
            "data_description": "SNP-phenotype association p-values curated from published GWAS", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gwascatalog", 
            "resource_url": "http://www.ebi.ac.uk/gwas/home"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of SNP-phenotype associations manually curated from published GWA Studies", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22139925", 
                "citation": "Li, M. J., Wang, P., Liu, X., Lim, E. L., Wang, Z., Yeager, M., Wong, M. P., Sham, P. C., Chanock, S. J. and J. Wang. (2012) GWASdb: a database for human genetic variants identified by genome-wide association studies. Nucleic Acids Res. 40:D1047-D1054.", 
                "author": "Li et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "ftp://jjwanglab.org/GWASdb/gwasdb_20140908_snp_trait.gz;ftp://jjwanglab.org/GWASdb/gwasdb_20140908_annotation.gz", 
            "resource_name": "GWASdb", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://jjwanglab.org/gwasdb/gwasdb2/gwasdb2/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by manual GWAS curation", 
            "number_of_genes": "0", 
            "data_description": "SNP-phenotype association p-values curated from published GWAS", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "gwasdb", 
            "resource_url": "http://jjwanglab.org/gwasdb"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "Heiser et al. have collected gene expression, genotype, and drug sensitivity data for 49 breast cancer cell lines.", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22003129", 
                "citation": "Heiser, L. M. et al. (2012) Subtype and pathway specific responses to anticancer compounds in breast cancer. Proceedings of the National Academy of Sciences of the United States of America. 109:2724-2729.", 
                "author": "Heiser et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.ebi.ac.uk/arrayexpress/files/E-MTAB-181/E-MTAB-181.processed.1.zip", 
            "resource_name": "Heiser", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.ebi.ac.uk/arrayexpress/files/E-MTAB-181/E-MTAB-181.idf.txt;http://www.ebi.ac.uk/arrayexpress/files/E-MTAB-181/E-MTAB-181.sdrf.txt", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by differential expression of gene across cell lines", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for breast cancer cell lines measured by microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "heiser", 
            "resource_url": "http://www.ebi.ac.uk/arrayexpress/experiments/E-MTAB-181/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "HMDB Metabolites", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "hmdbmetabolites", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of gene-phenotype associations extracted from published GWA studies by automated text-mining", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "18227866", 
                "citation": "Yu, W., Gwinn, M., Clyne, M., Yesupriya, A. and M. J. Khoury. (2008) A navigator for human genome epidemiology. Nat Genet. 40:124-125.", 
                "author": "Yu et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "HuGE Navigator", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.hugenavigator.net/HuGENavigator/downloadCenter.do", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by automated GWAS curation", 
            "number_of_genes": "0", 
            "data_description": "gene-phenotype associations extracted from published GWAS by automated text-mining", 
            "down_gene_set_library": null, 
            "notes": "must complete form at download page to download file", 
            "directory": "hugenavigator", 
            "resource_url": "http://www.hugenavigator.net/HuGENavigator/home.do"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Human Gene Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "humangeneatlas", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of abnormal human phenotypes annotated with diseases that cause the phenotypes and gene mutations that cause the diseases", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24217912", 
                "citation": "Kohler, S., Doelken, S. C., Mungall, C. J., Bauer, S., Firth, H. V., Bailleul-Forestier, I., Black, G. C., Brown, D. L., Brudno, M., Campbell, J., FitzPatrick, D. R., Eppig, J. T., Jackson, A. P., Freson, K., Girdea, M., Helbig, I., Hurst, J. A., Jahn, J., Jackson, L. G., Kelly, A. M., Ledbetter, D. H., Mansour, S., Martin, C. L., Moss, C., Mumford, A., Ouwehand, W. H., Park, S. M., Riggs, E. R., Scott, R. H., Sisodiya, S., Van Vooren, S., Wapner, R. J., Wilkie, A. O., Wright, C. F., Vulto-van Silfhout, A. T., de Leeuw, N., de Vries, B. B., Washingthon, N. L., Smith, C. L., Westerfield, M., Schofield, P., Ruef, B. J., Gkoutos, G. V., Haendel, M., Smedley, D., Lewis, S. E. and P. N. Robinson. (2014) The Human Phenotype Ontology project: linking molecular biology and disease through phenotype data. Nucleic Acids Res. 42:D966-D974.", 
                "author": "Kohler et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://purl.obolibrary.org/obo/hp.obo;http://compbio.charite.de/hudson/job/hpo.annotations/lastStableBuild/artifact/misc/phenotype_annotation.tab;http://compbio.charite.de/hudson/job/hpo.annotations.monthly/lastStableBuild/artifact/annotation/ALL_SOURCES_ALL_FREQUENCIES_genes_to_phenotype.txt", 
            "resource_name": "Human Phenotype Ontology (HPO)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.human-phenotype-ontology.org/contao/index.php/downloads.html;http://www.human-phenotype-ontology.org/contao/index.php/annotation-guide.html", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by mapping disease genes to disease phenotypes", 
            "number_of_genes": "0", 
            "data_description": "phenotype-causing gene mutations of human phenotypes from disease knowledgebases", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "hpo", 
            "resource_url": "http://www.human-phenotype-ontology.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein and gene expression data for human tissues, cell types and cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25613900", 
                "citation": "Uhlen, M., Fagerberg, L., Hallstrom, B. M., Lindskog, C., Oksvold, P., Mardinoglu, A., Sivertsson, A., Kampf, C., Sjostedt, E., Asplund, A., Olsson, I., Edlund, K.,Lundberg, E., Navani, S., Szigyarto, C. A., Odeberg, J., Djureinovic, D., Takanen, J. O., Hober, S., Alm, T., Edqvist, P. H., Berling, H., Tegel, H., Mulder, J., Rockberg, J., Nilsson, P., Schwenk, J. M., Hamsten, M., von Feilitzen, K., Forsberg, M., Persson, L., Johansson, F., Zwahlen, M., von Heijne, G., Nielsen, J. and F. Ponten. (2015) Tissue-based map of the human proteome. Science. 347:1260419.", 
                "author": "Uhlen et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "43", 
            "gene_list": null, 
            "data_url": "http://www.sciencemag.org/content/suppl/2015/01/21/347.6220.1260419.DC1/1260419__Excel_TablesS1-S18.xlsx;http://www.proteinatlas.org/download/rna.csv.zip", 
            "resource_name": "Human Protein Atlas (HPA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.sciencemag.org/content/suppl/2015/01/21/347.6220.1260419.DC1/1260419_Uhlen.SM.pdf;http://www.proteinatlas.org/about/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by differential expression of gene across cell lines", 
            "number_of_genes": "15372", 
            "data_description": "mRNA expression profiles for cell lines measured by RNA-Seq", 
            "down_gene_set_library": null, 
            "notes": "Supplementary Table 11 from the publication supplementary data file", 
            "directory": "hpacelllines", 
            "resource_url": "http://www.proteinatlas.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein and gene expression data for human tissues, cell types and cell lines", 
            "edge_list": null, 
            "attribute": "tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25613900", 
                "citation": "Uhlen, M., Fagerberg, L., Hallstrom, B. M., Lindskog, C., Oksvold, P., Mardinoglu, A., Sivertsson, A., Kampf, C., Sjostedt, E., Asplund, A., Olsson, I., Edlund, K.,Lundberg, E., Navani, S., Szigyarto, C. A., Odeberg, J., Djureinovic, D., Takanen, J. O., Hober, S., Alm, T., Edqvist, P. H., Berling, H., Tegel, H., Mulder, J., Rockberg, J., Nilsson, P., Schwenk, J. M., Hamsten, M., von Feilitzen, K., Forsberg, M., Persson, L., Johansson, F., Zwahlen, M., von Heijne, G., Nielsen, J. and F. Ponten. (2015) Tissue-based map of the human proteome. Science. 347:1260419.", 
                "author": "Uhlen et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "31", 
            "gene_list": null, 
            "data_url": "http://www.sciencemag.org/content/suppl/2015/01/21/347.6220.1260419.DC1/1260419__Excel_TablesS1-S18.xlsx;http://www.proteinatlas.org/download/rna.csv.zip", 
            "resource_name": "Human Protein Atlas (HPA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.sciencemag.org/content/suppl/2015/01/21/347.6220.1260419.DC1/1260419_Uhlen.SM.pdf;http://www.proteinatlas.org/about/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations by differential expression of gene across tissues", 
            "number_of_genes": "17426", 
            "data_description": "mRNA expression profiles for tissues measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": "Supplementary Table 1 from the publication supplementary data file", 
            "directory": "hpatissuesmrna", 
            "resource_url": "http://www.proteinatlas.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein and gene expression data for human tissues, cell types and cell lines", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25613900", 
                "citation": "Uhlen, M., Fagerberg, L., Hallstrom, B. M., Lindskog, C., Oksvold, P., Mardinoglu, A., Sivertsson, A., Kampf, C., Sjostedt, E., Asplund, A., Olsson, I., Edlund, K.,Lundberg, E., Navani, S., Szigyarto, C. A., Odeberg, J., Djureinovic, D., Takanen, J. O., Hober, S., Alm, T., Edqvist, P. H., Berling, H., Tegel, H., Mulder, J., Rockberg, J., Nilsson, P., Schwenk, J. M., Hamsten, M., von Feilitzen, K., Forsberg, M., Persson, L., Johansson, F., Zwahlen, M., von Heijne, G., Nielsen, J. and F. Ponten. (2015) Tissue-based map of the human proteome. Science. 347:1260419.", 
                "author": "Uhlen et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "121", 
            "gene_list": null, 
            "data_url": "http://www.sciencemag.org/content/suppl/2015/01/21/347.6220.1260419.DC1/1260419__Excel_TablesS1-S18.xlsx", 
            "resource_name": "Human Protein Atlas (HPA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.sciencemag.org/content/suppl/2015/01/21/347.6220.1260419.DC1/1260419_Uhlen.SM.pdf;http://www.proteinatlas.org/about/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "16658", 
            "data_description": "mRNA expression profiles for tissue samples measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": "Supplementary Table 18 from the publication supplementary data file", 
            "directory": "hpasamples", 
            "resource_url": "http://www.proteinatlas.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein and gene expression data for human tissues, cell types and cell lines", 
            "edge_list": null, 
            "attribute": "tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25613900", 
                "citation": "Uhlen, M., Fagerberg, L., Hallstrom, B. M., Lindskog, C., Oksvold, P., Mardinoglu, A., Sivertsson, A., Kampf, C., Sjostedt, E., Asplund, A., Olsson, I., Edlund, K.,Lundberg, E., Navani, S., Szigyarto, C. A., Odeberg, J., Djureinovic, D., Takanen, J. O., Hober, S., Alm, T., Edqvist, P. H., Berling, H., Tegel, H., Mulder, J., Rockberg, J., Nilsson, P., Schwenk, J. M., Hamsten, M., von Feilitzen, K., Forsberg, M., Persson, L., Johansson, F., Zwahlen, M., von Heijne, G., Nielsen, J. and F. Ponten. (2015) Tissue-based map of the human proteome. Science. 347:1260419.", 
                "author": "Uhlen et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "44", 
            "gene_list": null, 
            "data_url": "http://www.proteinatlas.org/download/normal_tissue.csv.zip", 
            "resource_name": "Human Protein Atlas (HPA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.proteinatlas.org/about/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations by differential expression of protein across tissues", 
            "number_of_genes": "15706", 
            "data_description": "semiquantitative protein expression profiles for tissues measured by immunohistochemistry", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "hpatissuesprotein", 
            "resource_url": "http://www.proteinatlas.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of human protein-protein interactions", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "18988627", 
                "citation": "Keshava Prasad, T. S., Goel, R., Kandasamy, K., Keerthikumar, S., Kumar, S., Mathivanan, S., Telikicherla, D., Raju, R., Shafreen, B., Venugopal, A., Balakrishnan, L., Marimuthu, A., Banerjee, S., Somanathan, D. S., Sebastian, A., Rani, S., Ray, S., Harrys Kishore, C. J., Kanth, S., Ahmed, M., Kashyap, M. K., Mohmood, R., Ramachandra, Y. L., Krishna, V., Rahiman, B. A., Mohan, S., Ranganathan, P., Ramabadran, S., Chaerkady, R. and A. Pandey. (2009) Human Protein Reference Database--2009 update. Nucleic Acids Research. 37:D767-772.", 
                "author": "Keshava Prasad et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.hprd.org/edownload/HPRD_Release9_041310", 
            "resource_name": "Human Protein Resource Database (HPRD)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.hprd.org/download", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from low-throughput or high-throughput protein-protein interaction data", 
            "number_of_genes": "0", 
            "data_description": "protein-protein interactions from low-throughput or high-throughput studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "hprd", 
            "resource_url": "http://www.hprd.org"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein expression data for tissues and cell lines measured by LC-MS/MS", 
            "edge_list": null, 
            "attribute": "tissue or cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24870542", 
                "citation": "Kim, M. S. et al. (2014) A draft map of the human proteome. Nature. 509:575-581.", 
                "author": "Kim et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Human Proteome Map (HPM)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.humanproteomemap.org/faqs.html", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue/cell line associations by differential expression of protein across tissues and cell lines", 
            "number_of_genes": "0", 
            "data_description": "protein expression profiles for tissues and cell lines measured by mass spectrometry", 
            "down_gene_set_library": null, 
            "notes": "must register for access", 
            "directory": "hpm", 
            "resource_url": "http://www.humanproteomemap.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of human metabolic pathways", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24225315", 
                "citation": "Caspi, R., Altman, T., Billington, R., Dreher, K., Foerster, H., Fulcher, C. A., Holland, T. A., Keseler, I. M., Kothari, A., Kubo, A., Krummenacker, M., Latendresse, M., Mueller, L. A., Ong, Q., Paley, S., Subhraveti, P., Weaver, D. S., Weerasinghe, D., Zhang, P. and P. D. Karp. (2014) The MetaCyc database of metabolic pathways and enzymes and the BioCyc collection of Pathway/Genome Databases. Nucleic Acids Res. 42:D459-D471.", 
                "author": "Caspi et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.humancyc.org/HUMAN/pathway-genes?object=PWY-6133", 
            "resource_name": "HumanCyc", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from curated pathways", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions by pathway", 
            "down_gene_set_library": null, 
            "notes": "scrape html at http://www.humancyc.org/HUMAN/class-instances?object=Pathways for pathway IDs and then query for lists of genes using Data URL example", 
            "directory": "humancycppi", 
            "resource_url": "http://www.humancyc.org"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of human metabolic pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24225315", 
                "citation": "Caspi, R., Altman, T., Billington, R., Dreher, K., Foerster, H., Fulcher, C. A., Holland, T. A., Keseler, I. M., Kothari, A., Kubo, A., Krummenacker, M., Latendresse, M., Mueller, L. A., Ong, Q., Paley, S., Subhraveti, P., Weaver, D. S., Weerasinghe, D., Zhang, P. and P. D. Karp. (2014) The MetaCyc database of metabolic pathways and enzymes and the BioCyc collection of Pathway/Genome Databases. Nucleic Acids Res. 42:D459-D471.", 
                "author": "Caspi et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "286", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://www.pathwaycommons.org/pc2/downloads/Pathway%20Commons.6.HumanCyc.GSEA.gmt.gz", 
            "resource_name": "HumanCyc", 
            "download_date": {
            "month": "12", 
            "day": "17", 
            "year": "2014"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://www.pathwaycommons.org/pc2/formats", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "932", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "humancycpathway", 
            "resource_url": "http://humancyc.org/;http://www.pathwaycommons.org/about/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Jaspar PWMs", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jaspar", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "gene-set enrichment analysis tool testing for putative regulatory kinases of query gene-sets", 
            "edge_list": null, 
            "attribute": "substrate", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "19176546", 
                "citation": "Lachmann, A. et al. (2009) KEA: kinase enrichment analysis. Bioinformatics. 25:684-686.", 
                "author": "Lachmann et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://amp.pharm.mssm.edu/Enrichr/geneSetLibrary?mode=text&libraryName=KEA", 
            "resource_name": "Kinase Enrichment Analysis (KEA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://amp.pharm.mssm.edu/lib/keacommandline.jsp", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "kinase-substrate associations from low-throughput or high-throughput phosphoproteomics data", 
            "number_of_genes": "0", 
            "data_description": "protein substrates of kinases from published low-throughput and high-throughput phosphoproteomics studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "kea", 
            "resource_url": "http://amp.pharm.mssm.edu/lib/kea.jsp"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project profiling percent inhibition of kinase activity by drugs", 
            "edge_list": null, 
            "attribute": "drug", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "21700206", 
                "citation": "Patricelli, M. P., Nomanbhoy, T. K., Wu, J., Brown, H., Zhou, D., Zhang, J., Jagannathan, S., Aban, A., Okerberg, E., Herring, C., Nordin, B., Weissig, H., Yang, Q., Lee, J. D., Gray, N. S. and J. W. Kozarich. (2011) In situ kinase profiling reveals functionally relevant properties of native kinases. Chem Biol. 18:699-710.", 
                "author": "Patricelli et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "small molecule", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://lincs.hms.harvard.edu/db/datasets/20087/results?search=&output_type=.csv", 
            "resource_name": "Kinativ", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://lincs.hms.harvard.edu/data/;https://docs.google.com/document/d/1R_d_1UWO0C9y1TceXpKIUkhjk08DfvP1D19txi4Tbas/edit", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "kinase-drug associations by percent inhibition of kinase by drug", 
            "number_of_genes": "0", 
            "data_description": "percent inhibition of kinases by drugs measured in cell lysates", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the template Data URL, using kinativ dataset IDs extracted from http://lincs.hms.harvard.edu/db/datasets/", 
            "directory": "kinativ", 
            "resource_url": "http://lincs.hms.harvard.edu/resources/assays/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project profiling percent inhibition of kinase activity by drugs", 
            "edge_list": null, 
            "attribute": "drug", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "15711537", 
                "citation": "Fabian, M.A. et al. (2005) A small molecule-kinase interaction map for clinical kinase inhibitors. Nature Biotechnology. 23:329-336.", 
                "author": "Fabian et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "small molecule", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://lincs.hms.harvard.edu/db/datasets/20020/results?search=&output_type=.csv", 
            "resource_name": "KinomeScan", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://lincs.hms.harvard.edu/data/;https://docs.google.com/document/d/1R_d_1UWO0C9y1TceXpKIUkhjk08DfvP1D19txi4Tbas/edit", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "kinase-drug associations by percent inhibition of kinase by drug", 
            "number_of_genes": "0", 
            "data_description": "percent inhibition of kinases by drugs measured using purified kinases", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the template Data URL, using kinomescan dataset IDs extracted from http://lincs.hms.harvard.edu/db/datasets/", 
            "directory": "kinomescan", 
            "resource_url": "http://lincs.hms.harvard.edu/data/kinomescan/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "published gene expression, copy number variation, and mutation data for 675 cancer cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25485619", 
                "citation": "Klijn, C. et al. (2015) A comprehensive transcriptional portrait of human cancer cell lines. Nat Biotechnol. 33:306-312.", 
                "author": "Klijn et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.nature.com/nbt/journal/v33/n3/extref/nbt.3080-S11.zip", 
            "resource_name": "Klijn", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.nature.com/nbt/journal/v33/n3/extref/nbt.3080-S1.pdf", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by differential expression of gene across cell lines", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for cancer cell lines measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "klijnmrna", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "published gene expression, copy number variation, and mutation data for 675 cancer cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25485619", 
                "citation": "Klijn, C. et al. (2015) A comprehensive transcriptional portrait of human cancer cell lines. Nat Biotechnol. 33:306-312.", 
                "author": "Klijn et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.nature.com/nbt/journal/v33/n3/extref/nbt.3080-S13.zip", 
            "resource_name": "Klijn", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.nature.com/nbt/journal/v33/n3/extref/nbt.3080-S1.pdf", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by mutation of gene in cell line", 
            "number_of_genes": "0", 
            "data_description": "gene mutations in cancer cell lines measured by SNP microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "klijnmut", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "published gene expression, copy number variation, and mutation data for 675 cancer cell lines", 
            "edge_list": null, 
            "attribute": "cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25485619", 
                "citation": "Klijn, C. et al. (2015) A comprehensive transcriptional portrait of human cancer cell lines. Nat Biotechnol. 33:306-312.", 
                "author": "Klijn et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.nature.com/nbt/journal/v33/n3/extref/nbt.3080-S14.zip", 
            "resource_name": "Klijn", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.nature.com/nbt/journal/v33/n3/extref/nbt.3080-S1.pdf", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell line associations by copy number variation of gene across cell lines", 
            "number_of_genes": "0", 
            "data_description": "gene-level copy number variation profiles for cancer cell lines measured by SNP microarray", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "klijncnv", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of curated pathways", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24214961", 
                "citation": "Kanehisa, M., Goto, S., Sato, Y., Kawashima, M., Furumichi, M. and M. Tanabe. (2014) Data, information, knowledge and principle: back to metabolism in KEGG. Nucleic Acids Research. 42:D199-205.", 
                "author": "Kanehisa et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Kyoto Encyclopedia of Genes and Genomes (KEGG)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from curated pathways", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions by pathway", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "keggppi", 
            "resource_url": "http://www.genome.jp/kegg/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of curated pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24214961", 
                "citation": "Kanehisa, M., Goto, S., Sato, Y., Kawashima, M., Furumichi, M. and M. Tanabe. (2014) Data, information, knowledge and principle: back to metabolism in KEGG. Nucleic Acids Research. 42:D199-205.", 
                "author": "Kanehisa et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "200", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": null, 
            "resource_name": "Kyoto Encyclopedia of Genes and Genomes (KEGG)", 
            "download_date": {
            "month": "08", 
            "day": "18", 
            "year": "2011"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "3947", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "keggpathway", 
            "resource_url": "http://www.genome.jp/kegg/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of gene expression profiles for cell lines following pharmacologic or genetic perturbation", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24906883", 
                "citation": "Duan, Q. et al. (2014) LINCS Canvas Browser: interactive web app to query, browse and interrogate LINCS L1000 gene expression signatures. Nucleic Acids Research.", 
                "author": "Duan et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "12505", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "LINCS L1000 Connectivity Map (CMAP)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://download.lincscloud.org/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations by differential expression of gene A following knockdown of gene B", 
            "number_of_genes": "3641", 
            "data_description": "mRNA expression profiles for cell lines following gene knockdown measured using the L1000 bead-based expression assay", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded from the cloud via Amazon S3", 
            "directory": "lincscmapgene", 
            "resource_url": "http://www.lincscloud.org/l1000/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of gene expression profiles for cell lines following pharmacologic or genetic perturbation", 
            "edge_list": null, 
            "attribute": "chemical", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24906883", 
                "citation": "Duan, Q. et al. (2014) LINCS Canvas Browser: interactive web app to query, browse and interrogate LINCS L1000 gene expression signatures. Nucleic Acids Research.", 
                "author": "Duan et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "chemical", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "LINCS L1000 Connectivity Map (CMAP)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://download.lincscloud.org/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-chemical associations by differential expression of gene following chemical perturbation", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for cell lines following  chemical perturbation measured using the L1000 bead-based expression assay", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded from the cloud via Amazon S3", 
            "directory": "lincscmapchemical", 
            "resource_url": "http://www.lincscloud.org/l1000/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein subcellular localization data", 
            "edge_list": null, 
            "attribute": "cellular compartment", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "17986452", 
                "citation": "Sprenger, J., Lynn Fink, J., Karunaratne, S., Hanson, K., Hamilton, N. A. and R. D. Teasdale. (2008) LOCATE: a mammalian protein subcellular localization database. Nucleic Acids Res. 36:D230-D233.", 
                "author": "Sprenger et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://locate.imb.uq.edu.au/info_files/LOCATE_mouse_v6_20081121.xml.zip;http://locate.imb.uq.edu.au/info_files/LOCATE_human_v6_20081121.xml.zip", 
            "resource_name": "LOCATE", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://locate.imb.uq.edu.au/downloads.shtml;http://locate.imb.uq.edu.au/info_files/LOCATE_v6.xsd", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cellular compartment associations from low-throughput or high-throughput protein localization assays", 
            "number_of_genes": "0", 
            "data_description": "subcellular localization of proteins from low-throughput or high-throughput protein localization assays", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "locate", 
            "resource_url": "http://locate.imb.uq.edu.au/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of mouse genotypes annotated with mouse phenotype terms selected from a controlled vocabulary", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "phenotype", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24285300", 
                "citation": "Blake, J.A. et al. (2014) The Mouse Genome Database: integration of and access to knowledge about the laboratory mouse. Nucleic Acids Research. 42:D810-817.", 
                "author": "Blake et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "8579", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "ftp://ftp.informatics.jax.org/pub/reports/MGI_PhenoGenoMP.rpt;ftp://ftp.informatics.jax.org/pub/reports/MRK_List2.rpt;ftp://ftp.informatics.jax.org/pub/reports/HMD_HumanPhenotype.rpt;ftp://ftp.informatics.jax.org/pub/reports/MGI_PhenotypicAllele.rpt;ftp://ftp.informatics.jax.org/pub/reports/MPheno_OBO.ontology", 
            "resource_name": "Mammalian Phenotype Ontology (MPO)", 
            "download_date": {
            "month": "02", 
            "day": "09", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "ftp://ftp.informatics.jax.org/pub/reports/index.html#pheno", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations from phenotyping studies of knockout mice", 
            "number_of_genes": "7798", 
            "data_description": "observed phenotypes of transgenic mice collected from mouse phenotyping studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "mgimpo", 
            "resource_url": "http://www.informatics.jax.org/phenotypes.shtml"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of experimentally validated microRNA targets", 
            "edge_list": null, 
            "attribute": "microRNA", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24304892", 
                "citation": "Hsu, S. D., Tseng, Y. T., Shrestha, S., Lin, Y. L., Khaleel, A., Chou, C. H., Chu, C. F., Huang, H. Y., Lin, C. M., Ho, S. Y., Jian, T. Y., Lin, F. M., Chang, T. H., Weng, S. L., Liao, K. W., Liao, I. E., Liu, C. C. and H. D. Huang. (2014) miRTarBase update 2014: an information resource for experimentally validated miRNA-target interactions. Nucleic Acids Res. 42:D78-D85.", 
                "author": "Hsu et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://mirtarbase.mbc.nctu.edu.tw/cache/download/4.5/hsa_MTI.xls", 
            "resource_name": "MiRTarBase", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://mirtarbase.mbc.nctu.edu.tw/php/download.php", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-microRNA associations from low-throughput or high-throughput microRNA targeting studies", 
            "number_of_genes": "0", 
            "data_description": "target genes of microRNAs from published experiments", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "mirtarbase", 
            "resource_url": "http://mirtarbase.mbc.nctu.edu.tw/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of biomolecular interactions", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22121220", 
                "citation": "Kerrien, S., Aranda, B., Breuza, L., Bridge, A., Broackes-Carter, F., Chen, C., Duesbury, M., Dumousseau, M., Feuermann, M., Hinz, U., Jandrasits, C., Jimenez, R. C., Khadake, J., Mahadevan, U., Masson, P., Pedruzzi, I., Pfeiffenberger, E., Porras, P., Raghunath, A., Roechert, B., Orchard, S. and H. Hermjakob. (2012) The IntAct molecular interaction database in 2012. Nucleic Acids Research. 40:D841-846.", 
                "author": "Kerrien et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "ftp://ftp.ebi.ac.uk/pub/databases/intact/current/psimitab/intact-micluster.zip", 
            "resource_name": "Molecular Interaction Database (IntAct)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "ftp://ftp.ebi.ac.uk/pub/databases/intact/current/psimitab/README", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from low-throughput or high-throughput protein-protein interaction data", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions from low-throughput or high-throughput studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "intact", 
            "resource_url": "http://www.ebi.ac.uk/intact/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of transcription factor binding sites predicted using known transcription factor binding site motifs", 
            "edge_list": null, 
            "attribute": "transcription factor", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "19017655", 
                "citation": "Xie, X., Rigor, P. and P. Baldi. (2009) MotifMap: a human genome-wide map of candidate regulatory motif sites. Bioinformatics. 25:167:174.", 
                "author": "Xie et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "332", 
            "gene_list": null, 
            "data_url": "http://www.igb.uci.edu/~motifmap/motifmap/HUMAN/hg19/multiz46way_placental/HUMAN.hg19_multiz46way.tar.bz2", 
            "resource_name": "MotifMap", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-transcription factor associations predicted using known transcription factor binding site motifs", 
            "number_of_genes": "20432", 
            "data_description": "target genes of transcription factors  predicted using known transcription factor binding site motifs", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "motifmap", 
            "resource_url": "http://motifmap.ics.uci.edu/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Mouse Gene Atlas", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "mousegeneatlas", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "MSigDB Computational", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "msigdbcomp", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "MSigDB Oncogenic Signatures", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "msigdbonc", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "NCI-60 Cancer Cell Lines", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "nci60", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase for nuclear receptors, their ligands, and coregulators", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "21620140", 
                "citation": "Malovannaya, A. et al. (2011) Analysis of the human endogenous coregulator complexome. Cell. 145:787-799.", 
                "author": "Malovannaya et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.sciencedirect.com/science/MiamiMultiMediaURL/1-s2.0-S0092867411005320/1-s2.0-S0092867411005320-mmc1.xls/272196/FULL/S0092867411005320/340a6aa086272fd1d492a1e19a9b412b/mmc1.xls;http://www.sciencedirect.com/science/MiamiMultiMediaURL/1-s2.0-S0092867411005320/1-s2.0-S0092867411005320-mmc2.xls/272196/FULL/S0092867411005320/1abd8960d2caecdf38c60de1b0a08cdc/mmc2.xls", 
            "resource_name": "Nuclear Receptor Signaling Atlas (NURSA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.nursa.org/nursa/datasets/dataset.jsf?doi=10.1621/datasets.06003", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations by identification of protein A in complex with protein B using IP-MS", 
            "number_of_genes": "0", 
            "data_description": "proteins identified by mass spectrometry in complexes isolated from cultured cells by immuno-precipitation", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "nursa", 
            "resource_url": "http://www.nursa.org/nursa/index.jsf"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase for heritable human diseases and other phenotypes", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "21472891", 
                "citation": "Amberger, J. et al. (2011) A new face and new challenges for Online Mendelian Inheritance in Man (OMIM(R)). Human Mutation. 32:564-567.", 
                "author": "Amberger et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Online Mendelian Inheritance in Man (OMIM)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.omim.org/downloads", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by literature evidence for phenotype-causing gene mutations", 
            "number_of_genes": "0", 
            "data_description": "disease- or phenotype-causing gene mutations for heritable human diseases or phenotypes curated from biomedical publications", 
            "down_gene_set_library": null, 
            "notes": "must register for access", 
            "directory": "omim", 
            "resource_url": "http://www.omim.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein families, phylogenetic trees, functions, and pathways", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23193289", 
                "citation": "Mi, H., Muruganujan, A. and P. D. Thomas. (2013) PANTHER in 2013: modeling the evolution of gene function, and other gene attributes, in the context of phylogenetic trees. Nucleic Acids Res. 41:D377-D386.", 
                "author": "Mi et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "ftp://ftp.pantherdb.org//pathway/current_release/BioPAX.tar.gz", 
            "resource_name": "PANTHER", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "ftp://ftp.pantherdb.org//pathway/current_release/README", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from curated pathways", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions by pathway", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "pantherppi", 
            "resource_url": "http://www.pantherdb.org"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein families, phylogenetic trees, functions, and pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "23193289", 
                "citation": "Mi, H., Muruganujan, A. and P. D. Thomas. (2013) PANTHER in 2013: modeling the evolution of gene function, and other gene attributes, in the context of phylogenetic trees. Nucleic Acids Res. 41:D377-D386.", 
                "author": "Mi et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "145", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://www.pathwaycommons.org/pc2/downloads/Pathway%20Commons.6.PANTHER%20Pathway.GSEA.gmt.gz", 
            "resource_name": "PANTHER", 
            "download_date": {
            "month": "12", 
            "day": "17", 
            "year": "2014"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://www.pathwaycommons.org/pc2/formats", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "1962", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "pantherpathway", 
            "resource_url": "http://pantherdb.org/;http://www.pathwaycommons.org/about/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of biological pathways and biomolecular interactions aggregated from many source databases", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "21071392", 
                "citation": "Cerami, E. G., Gross, B. E., Demir, E., Rodchenkov, I., Babur, O., Anwar, N., Schultz, N., Bader, G. D. and C. Sander. (2011) Pathway Commons, a web resource for biological pathway data. Nucleic Acids Res. 39:D685-D690.", 
                "author": "Cerami et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "15747", 
            "gene_list": null, 
            "data_url": "http://www.pathwaycommons.org/pc2/downloads/Pathway%20Commons.7.All.EXTENDED_BINARY_SIF.hgnc.sif.gz", 
            "resource_name": "Pathway Commons (PC)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.pathwaycommons.org/pc2/formats;http://www.pathwaycommons.org/pc2/downloads/datasources.txt", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from low-throughput or high-throughput protein-protein interaction data", 
            "number_of_genes": "15747", 
            "data_description": "protein-protein interactions from low-throughput or high-throughput studies aggregated by Pathway Commons from the following databases: Reactome, NCI Pathways, PhosphoSite, HumanCyc, HPRD, PANTHER, DIP, BioGRID, IntAct, BIND, Transfac, MiRTarBase, Drugbank, Recon X, Comparative Toxicogenomics Database, and KEGG", 
            "down_gene_set_library": null, 
            "notes": "extracted only interactions classified as controls-state-change-of, controls-transport-of, controls-phosphorylation-of, or interacts-with", 
            "directory": "pc", 
            "resource_url": "http://www.pathwaycommons.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of curated pathways", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "18832364", 
                "citation": "Schaefer, C. F., Anthony, K., Krupa, S., Buchoff, J., Day, M., Hannay, T. and K. H. Buetow. (2009) PID: the Pathway Interaction Database. Nucleic Acids Res. 37:D674-679.", 
                "author": "Schaefer et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "ftp://ftp1.nci.nih.gov/pub/PID/BioPAX_Level_3/NCI-Nature_Curated.bp3.owl.gz", 
            "resource_name": "Pathway Interaction Database (PID)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://pid.nci.nih.gov/userguide/output_formats.shtml", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from curated pathways", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions by pathway", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "pidppi", 
            "resource_url": "http://pid.nci.nih.gov/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of curated pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "18832364", 
                "citation": "Schaefer, C. F., Anthony, K., Krupa, S., Buchoff, J., Day, M., Hannay, T. and K. H. Buetow. (2009) PID: the Pathway Interaction Database. Nucleic Acids Res. 37:D674-679.", 
                "author": "Schaefer et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "223", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "ftp://ftp1.nci.nih.gov/pub/PID/uniprot/uniprot.tab.gz", 
            "resource_name": "Pathway Interaction Database (PID)", 
            "download_date": {
            "month": "12", 
            "day": "17", 
            "year": "2014"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://pid.nci.nih.gov/userguide/output_formats.shtml", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "2510", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "pidpathway", 
            "resource_url": "http://pid.nci.nih.gov/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Human Dephosphorylation Database (DEPOD)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "depod", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "dataset of occurrence frequencies for biological terms in abstracts of publications describing phosphosites", 
            "edge_list": null, 
            "attribute": "biological term", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Phosphosite Textmining", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-biological term associations by occurrence of biological term in abstracts of publications describing phosphosite on protein", 
            "number_of_genes": "0", 
            "data_description": "occurrence frequencies for biological terms in abstracts of publications describing phosphosites", 
            "down_gene_set_library": null, 
            "notes": "un-published data collection created by Nick Fernandez at the Ma'ayan Lab", 
            "directory": "phosphositetextmining", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase of protein post-translational modifications", 
            "edge_list": null, 
            "attribute": "phosphorylated protein", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25514926", 
                "citation": "Hornbeck, P. V., Zhang, B., Murray, B., Kornhauser, J. M., Latham, V. and E. Skrzypek. (2015) PhosphoSitePlus, 2014: mutations, PTMs and recalibrations. Nucleic Acids Res. 43:D512-520.", 
                "author": "Hornbeck et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.phosphosite.org/downloads/Kinase_Substrate_Dataset.gz", 
            "resource_name": "PhosphoSitePlus", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.phosphosite.org/staticDownloads.do", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "kinase-substrate associations from low-throughput or high-throughput phosphoproteomics data", 
            "number_of_genes": "0", 
            "data_description": "kinase-substrate interactions curated from low-throughput or high-throughput phosphoproteomics studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "phosphositeplus", 
            "resource_url": "http://www.phosphosite.org"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "PPI Hub Proteins", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "hubs", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of protein expression data for tissues and cell lines aggregated from many proteomics studies", 
            "edge_list": null, 
            "attribute": "tissue or cell line", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24870543", 
                "citation": "Wilhelm, M. et al. (2014) Mass-spectrometry-based draft of the human proteome. Nature. 509:582-587.", 
                "author": "Wilhelm et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Proteomics Database (ProteomicsDB)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "https://www.proteomicsdb.org/#api", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue/cell line associations by differential expression of protein across tissues and cell lines", 
            "number_of_genes": "0", 
            "data_description": "protein expression profiles for tissues and cell lines reprocessed from many proteomics datasets", 
            "down_gene_set_library": null, 
            "notes": "data were downloaded using the proteomicsdb api", 
            "directory": "proteomicsdb", 
            "resource_url": "https://www.proteomicsdb.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of curated pathways", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24243840", 
                "citation": "Croft, D., Mundo, A. F., Haw, R., Milacic, M., Weiser, J., Wu, G., Caudy, M., Garapati, P., Gillespie, M., Kamdar, M. R., Jassal, B., Jupe, S., Matthews, L., May, B., Palatnik, S., Rothfels, K., Shamovsky, V., Song, H., Williams, M., Birney, E., Hermjakob, H., Stein, L. and P. D'Eustachio. (2014) The Reactome pathway knowledgebase. Nucleic Acids Research. 42:D472-477.", 
                "author": "Croft et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://www.reactome.org/download/current/homo_sapiens.mitab.interactions.txt.gz", 
            "resource_name": "Reactome", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://wiki.reactome.org/index.php/PSI-MITAB_interactions", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from curated pathways", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions by pathway", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "reactomeppi", 
            "resource_url": "http://www.reactome.org"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of curated pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "24243840", 
                "citation": "Croft, D., Mundo, A. F., Haw, R., Milacic, M., Weiser, J., Wu, G., Caudy, M., Garapati, P., Gillespie, M., Kamdar, M. R., Jassal, B., Jupe, S., Matthews, L., May, B., Palatnik, S., Rothfels, K., Shamovsky, V., Song, H., Williams, M., Birney, E., Hermjakob, H., Stein, L. and P. D'Eustachio. (2014) The Reactome pathway knowledgebase. Nucleic Acids Research. 42:D472-477.", 
                "author": "Croft et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "1638", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://www.reactome.org/download/current/ReactomePathways.gmt.zip", 
            "resource_name": "Reactome", 
            "download_date": {
            "month": "12", 
            "day": "17", 
            "year": "2014"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://www.reactome.org/pages/download-data/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "7535", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "reactomepathway", 
            "resource_url": "http://www.reactome.org"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "network model of human metabolism", 
            "edge_list": null, 
            "attribute": "gene", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "23455439", 
                "citation": "Thiele, I., Swainston, N., Fleming, R. M., Hoppe, A., Sahoo, S., Aurich, M. K., Haraldsdottir, H., Mo, M. L., Rolfsson, O., Stobbe, M. D., Thorleifsson, S. G., Agren, R., Bolling, C., Bordel, S., Chavali, A. K., Dobson, P., Dunn, W. B., Endler, L., Hala, D., Hucka, M., Hull, D., Jameson, D., Jamshidi, N., Jonsson, J. J., Juty, N., Keating, S., Nookaew, I., Le Novere, N., Malys, N., Mazein, A., Papin, J. A., Price, N. D., Selkov, E. Sr., Sigurdsson, M. I., Simeonidis, E., Sonnenschein, N., Smallbone, K., Sorokin, A., van Beek, J. H., Weichart, D., Goryanin, I., Nielsen, J., Westerhoff, H. V., Kell, D. B., Mendes, P. and B. O. Palsson. (2013) A community-driven global reconstruction of human metabolism. Nat Biotechnol. 31:419-425.", 
                "author": "Thiele et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "http://humanmetabolism.org/wp-content/uploads/2013/08/Recon2.v03.mat_.zip", 
            "resource_name": "Recon X", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://humanmetabolism.org/?page_id=75", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-gene associations from reconstructed metabolic network", 
            "number_of_genes": "0", 
            "data_description": "biomolecular interactions from network reconstruction", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "reconx", 
            "resource_url": "http://humanmetabolism.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping the distribution of histones, DNA binding factors, and accessory proteins on the human genome, as well as the genomic distribution of covalent modifications on DNA and nucleoproteins, for stem cells, differentiated cells, and primary tissues", 
            "edge_list": null, 
            "attribute": "cell or tissue sample (sample defined by cell or tissue type and histone modification)", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25693563", 
                "citation": "Roadmap Epigenomics Consortium et al. (2015) Integrative analysis of 111 reference human epigenomes. Nature. 518:317-330.", 
                "author": "Roadmap Epigenomics Consortium et al. et al."
            }, 
            {
                "pmid": "20944595", 
                "citation": "Bernstein, B. E., Stamatoyannopoulos, J. A., Costello, J. F., Ren, B., Milosavljevic, A., Meissner, A., Kellis, M., Marra, M. A., Beaudet, A. L., Ecker, J. R., Farnham, P. J., Hirst, M., Lander, E. S., Mikkelsen, T. S. and J. A. Thomson. (2010) The NIH Roadmap Epigenomics Mapping Consortium. Nat Biotechnol. 28:1045-1048.", 
                "author": "Bernstein et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Roadmap Epigenomics", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://egg2.wustl.edu/roadmap/web_portal/processed_data.html", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell or tissue sample associations by differential modification of histones near gene across cell and tissue samples", 
            "number_of_genes": "0", 
            "data_description": "histone modification profiles for primary cells and tissues measured by ChIP-seq", 
            "down_gene_set_library": null, 
            "notes": "need to complete", 
            "directory": "epigenomicshm", 
            "resource_url": "http://www.roadmapepigenomics.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping the distribution of histones, DNA binding factors, and accessory proteins on the human genome, as well as the genomic distribution of covalent modifications on DNA and nucleoproteins, for stem cells, differentiated cells, and primary tissues", 
            "edge_list": null, 
            "attribute": "cell or tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25693563", 
                "citation": "Roadmap Epigenomics Consortium et al. (2015) Integrative analysis of 111 reference human epigenomes. Nature. 518:317-330.", 
                "author": "Roadmap Epigenomics Consortium et al. et al."
            }, 
            {
                "pmid": "20944595", 
                "citation": "Bernstein, B. E., Stamatoyannopoulos, J. A., Costello, J. F., Ren, B., Milosavljevic, A., Meissner, A., Kellis, M., Marra, M. A., Beaudet, A. L., Ecker, J. R., Farnham, P. J., Hirst, M., Lander, E. S., Mikkelsen, T. S. and J. A. Thomson. (2010) The NIH Roadmap Epigenomics Mapping Consortium. Nat Biotechnol. 28:1045-1048.", 
                "author": "Bernstein et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Roadmap Epigenomics", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://egg2.wustl.edu/roadmap/web_portal/processed_data.html", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell or tissue associations by differential methylation of gene across cell and tissue types", 
            "number_of_genes": "0", 
            "data_description": "DNA methylation profiles for primary cells and tissues measured by bisulfite sequencing, MeDIP-seq or MRE-seq", 
            "down_gene_set_library": null, 
            "notes": "need to complete", 
            "directory": "epigenomicsdnamethylation", 
            "resource_url": "http://www.roadmapepigenomics.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping the distribution of histones, DNA binding factors, and accessory proteins on the human genome, as well as the genomic distribution of covalent modifications on DNA and nucleoproteins, for stem cells, differentiated cells, and primary tissues", 
            "edge_list": null, 
            "attribute": "cell or tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25693563", 
                "citation": "Roadmap Epigenomics Consortium et al. (2015) Integrative analysis of 111 reference human epigenomes. Nature. 518:317-330.", 
                "author": "Roadmap Epigenomics Consortium et al. et al."
            }, 
            {
                "pmid": "20944595", 
                "citation": "Bernstein, B. E., Stamatoyannopoulos, J. A., Costello, J. F., Ren, B., Milosavljevic, A., Meissner, A., Kellis, M., Marra, M. A., Beaudet, A. L., Ecker, J. R., Farnham, P. J., Hirst, M., Lander, E. S., Mikkelsen, T. S. and J. A. Thomson. (2010) The NIH Roadmap Epigenomics Mapping Consortium. Nat Biotechnol. 28:1045-1048.", 
                "author": "Bernstein et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Roadmap Epigenomics", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://egg2.wustl.edu/roadmap/web_portal/processed_data.html", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell or tissue associations by differential accessibility of gene across cell and tissue types", 
            "number_of_genes": "0", 
            "data_description": "DNA accessibility profiles for primary cells and tissues measured by Dnase-seq", 
            "down_gene_set_library": null, 
            "notes": "need to complete", 
            "directory": "epigenomicsdnaaccessibility", 
            "resource_url": "http://www.roadmapepigenomics.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project mapping the distribution of histones, DNA binding factors, and accessory proteins on the human genome, as well as the genomic distribution of covalent modifications on DNA and nucleoproteins, for stem cells, differentiated cells, and primary tissues", 
            "edge_list": null, 
            "attribute": "cell or tissue", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25693563", 
                "citation": "Roadmap Epigenomics Consortium et al. (2015) Integrative analysis of 111 reference human epigenomes. Nature. 518:317-330.", 
                "author": "Roadmap Epigenomics Consortium et al. et al."
            }, 
            {
                "pmid": "20944595", 
                "citation": "Bernstein, B. E., Stamatoyannopoulos, J. A., Costello, J. F., Ren, B., Milosavljevic, A., Meissner, A., Kellis, M., Marra, M. A., Beaudet, A. L., Ecker, J. R., Farnham, P. J., Hirst, M., Lander, E. S., Mikkelsen, T. S. and J. A. Thomson. (2010) The NIH Roadmap Epigenomics Mapping Consortium. Nat Biotechnol. 28:1045-1048.", 
                "author": "Bernstein et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Roadmap Epigenomics", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://egg2.wustl.edu/roadmap/web_portal/processed_data.html", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-cell or tissue associations by differential expression of gene across cell and tissue types", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for primary cells and tissues measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": "need to complete", 
            "directory": "epigenomicsmrna", 
            "resource_url": "http://www.roadmapepigenomics.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "collection of data from published SILAC experiments that measured protein phosphorylation changes in cell lines following experimental perturbation", 
            "edge_list": null, 
            "attribute": "perturbation", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "perturbation", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "SILAC Phosphoproteomics", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-perturbation associations by differential phosphorylation of protein across perturbations", 
            "number_of_genes": "0", 
            "data_description": "phosphorylation levels of proteins in cell lines following perturbation measured by SILAC", 
            "down_gene_set_library": null, 
            "notes": "un-published data collection created by Nick Fernandez at the Ma'ayan Lab", 
            "directory": "silac", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "wiki of information about SNPs", 
            "edge_list": null, 
            "attribute": "phenotype", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "22140107", 
                "citation": "Cariaso, M. and G. Lennon. (2012) SNPedia: a wiki supporting personal genome annotation, interpretation and analysis. Nucleic Acids Res. 40:D1308-D1312.", 
                "author": "Cariaso et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "phenotype", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "SNPedia", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://snpedia.com/index.php/Bulk", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-phenotype associations by crowd-sourced curation", 
            "number_of_genes": "0", 
            "data_description": "SNP-phenotype associations curated by SNPedia users from various sources", 
            "down_gene_set_library": null, 
            "notes": "data downloaded using the SNPedia api", 
            "directory": "snpedia", 
            "resource_url": "http://snpedia.com/index.php/SNPedia"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "TargetScan microRNA", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "targetscan", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "TF-LOF Expression from GEO", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "geotflof", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "project collecting clinical, genomic, proteomic, and epigenomic data for cancer patients and tumor samples", 
            "edge_list": null, 
            "attribute": "tissue sample", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "24071849", 
                "citation": "Cancer Genome Atlas Research Network et al. (2013) The Cancer Genome Atlas Pan-Cancer analysis project. Nat Genet. 45:1113-1120.", 
                "author": "Cancer Genome Atlas Research Network et al. et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": "https://tcga-data.nci.nih.gov/tcga/findArchives.htm", 
            "resource_name": "The Cancer Genome Atlas (TCGA)", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "https://wiki.nci.nih.gov/display/TCGA/TCGA+Bulk+Download+User%27s+Guide;jsessionid=7BCF3B975163016B14289802F9847CA4;https://tcga-data.nci.nih.gov/annotations/", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-tissue sample associations by differential expression of gene across tissue samples", 
            "number_of_genes": "0", 
            "data_description": "mRNA expression profiles for tumor and normal tissue samples measured by RNA-seq", 
            "down_gene_set_library": null, 
            "notes": "choose data of interest using bulk download form", 
            "directory": "tcga", 
            "resource_url": "https://tcga-data.nci.nih.gov/tcga/"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein expression evidence for tissues and cell types collected by integrating experimental datasets, by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "tissue", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": null, 
                "citation": "Santos, A., Tsafou, K., Stolte, C., Frankild, S., O'Donoghue, S. and L. J. Jensen. (2014) Comprehensive comparison of large-scale tissue expression datasets. Biorxiv Preprint. doi:10.1101/010975.", 
                "author": "Santos et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "4187", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_tissue_textmining_full.tsv", 
            "resource_name": "TISSUES", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://tissues.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations from automated text-mining of biomedical literature", 
            "number_of_genes": "16184", 
            "data_description": "gene-tissue co-occurrence scores from text-mining biomedical abstracts", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensentissuetextmining", 
            "resource_url": "http://tissues.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein expression evidence for tissues and cell types collected by integrating experimental datasets, by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "tissue", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": null, 
                "citation": "Santos, A., Tsafou, K., Stolte, C., Frankild, S., O'Donoghue, S. and L. J. Jensen. (2014) Comprehensive comparison of large-scale tissue expression datasets. Biorxiv Preprint. doi:10.1101/010975.", 
                "author": "Santos et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "643", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_tissue_knowledge_full.tsv", 
            "resource_name": "TISSUES", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://tissues.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations by integrating evidence from manual literature curation", 
            "number_of_genes": "16216", 
            "data_description": "protein tissue expression evidence scores by manual literature curation", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensentissuecurated", 
            "resource_url": "http://tissues.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of protein expression evidence for tissues and cell types collected by integrating experimental datasets, by manual literature curation, or by automated textmining", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "tissue", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": null, 
                "citation": "Santos, A., Tsafou, K., Stolte, C., Frankild, S., O'Donoghue, S. and L. J. Jensen. (2014) Comprehensive comparison of large-scale tissue expression datasets. Biorxiv Preprint. doi:10.1101/010975.", 
                "author": "Santos et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "tissue or cell line", 
            "number_of_attributes": "243", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://download.jensenlab.org/human_tissue_experiments_full.tsv", 
            "resource_name": "TISSUES", 
            "download_date": {
            "month": "02", 
            "day": "02", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://tissues.jensenlab.org/About", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-tissue associations by integrating evidence from experimental studies", 
            "number_of_genes": "15505", 
            "data_description": "protein tissue expression evidence scores by integrating experimental data", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "jensentissueexpts", 
            "resource_url": "http://tissues.jensenlab.org/Search"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "database of transcription factors, binding sites, and target genes", 
            "edge_list": null, 
            "attribute": "transcription factor", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "16381825", 
                "citation": "Matys, V., Kel-Margoulis, O. V., Fricke, E., Liebich, I., Land, S., Barre-Dirrie, A., Reuter, I., Chekmenev, D., Krull, M., Hornischer, K., Voss, N., Stegmaier, P., Lewicki-Potapov, B., Saxel, H., Kel, A. E. and E. Wingender. (2006) TRANSFAC and its module TRANSCompel: transcriptional gene regulation in eukaryotes. Nucleic Acids Res. 34:D108-D110.", 
                "author": "Matys et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "gene", 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "TRANSFAC", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-transcription factor associations from low-throughput or high-throughput transcription factor functional studies", 
            "number_of_genes": "0", 
            "data_description": "target genes of transcription factors manually curated from low-throughput or high-throughput transcription factor functional studies", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "transfac", 
            "resource_url": "http://www.gene-regulation.com/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Transfac PWMs", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "transfacpwm", 
            "resource_url": null
        }, 
        {
            "gene_set_library": null, 
            "resource_description": "knowledgebase for proteins, including protein sequences, structural annotations, and functional annotations", 
            "edge_list": null, 
            "attribute": "protein domain", 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": "25348405", 
                "citation": "UniProt Consortium. (2015) UniProt: a hub for protein information. Nucleic Acids Res. 43:D204-212", 
                "author": "UniProt Consortium. et al."
            }, 
            {
                "pmid": "25428371", 
                "citation": "Mitchell, A. et al. (2015) The InterPro protein families database: the classification resource after 15 years. Nucleic Acids Res. 43:D213-221.", 
                "author": "Mitchell et al."
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": "structural feature", 
            "number_of_attributes": "11015", 
            "gene_list": null, 
            "data_url": "http://www.uniprot.org/uniprot/?query=organism%3A%22Homo+sapiens+%5B9606%5D%22+AND+%22complete+proteome%22&sort=score", 
            "resource_name": "Universal Protein Resource (Uniprot)", 
            "download_date": {
            "month": "03", 
            "day": "24", 
            "year": "2015"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": "http://www.uniprot.org/help/domain", 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": "gene-protein domain associations by sequence similarity to domain signatures", 
            "number_of_genes": "18002", 
            "data_description": "protein domains predicted for gene products based on sequence similarity to known domain signatures", 
            "down_gene_set_library": null, 
            "notes": "from search bar, go to advanced search, select organism as first filter and choose homo sapiens then select all as second filter and type complete proteome, after getting results, at left column, filter for just Reviewed, Swiss-Prot entries (~20,000), then at top of table, click the columns button, expand the Family and domain section and check Interpro, then return to the results table and download the table to a text file, filter results for entries that have an InterPro domain annotation", 
            "directory": "uniprot", 
            "resource_url": "http://www.uniprot.org/"
        }, 
        {
            "gene_set_library": null, 
            "resource_description": null, 
            "edge_list": null, 
            "attribute": null, 
            "attribute_table": null, 
            "publication": [
            {
                "pmid": null, 
                "citation": null, 
                "author": null
            }
            ], 
            "script": null, 
            "attribute_similarity_matrix": null, 
            "attribute_list": null, 
            "up_gene_set_library": null, 
            "attribute_class": null, 
            "number_of_attributes": "0", 
            "gene_list": null, 
            "data_url": null, 
            "resource_name": "Virus MINT", 
            "download_date": {
            "month": "MM", 
            "day": "DD", 
            "year": "YYYY"
            }, 
            "gene_similarity_matrix": null, 
            "readme_url": null, 
            "down_attribute_set_library": null, 
            "attribute_set_library": null, 
            "up_attribute_set_library": null, 
            "association": null, 
            "number_of_genes": "0", 
            "data_description": null, 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "virusmint", 
            "resource_url": null
        }, 
        {
            "gene_set_library": "gene_set_library_crisp.gmt.gz", 
            "resource_description": "database of curated pathways", 
            "edge_list": "gene_attribute_edges.txt.gz", 
            "attribute": "pathway", 
            "attribute_table": "gene_attribute_matrix.txt.gz", 
            "publication": [
            {
                "pmid": "22096230", 
                "citation": "Kelder, T., van Iersel, M. P., Hanspers, K., Kutmon, M., Conklin, B. R., Evelo, C. T. and A. R. Pico. (2012) WikiPathways: building research communities on biological pathways. Nucleic Acids Research. 40:D1301-1307.", 
                "author": "Kelder et al."
            }
            ], 
            "script": "processing_script.m", 
            "attribute_similarity_matrix": "attribute_similarity_matrix_cosine.txt.gz", 
            "attribute_list": "attribute_list.txt.gz", 
            "up_gene_set_library": null, 
            "attribute_class": "functional phrase", 
            "number_of_attributes": "427", 
            "gene_list": "gene_list.txt.gz", 
            "data_url": "http://pathvisio.org/data/bots/gmt/wikipathways.gmt", 
            "resource_name": "Wikipathways", 
            "download_date": {
            "month": "12", 
            "day": "17", 
            "year": "2014"
            }, 
            "gene_similarity_matrix": "gene_similarity_matrix_cosine.txt.gz", 
            "readme_url": "http://www.wikipathways.org/index.php/Download_Pathways", 
            "down_attribute_set_library": null, 
            "attribute_set_library": "attribute_set_library_crisp.gmt.gz", 
            "up_attribute_set_library": null, 
            "association": "gene-pathway associations from curated pathways", 
            "number_of_genes": "6097", 
            "data_description": "sets of proteins participating in pathways", 
            "down_gene_set_library": null, 
            "notes": null, 
            "directory": "wikipathways", 
            "resource_url": "http://www.wikipathways.org"
        }
    ]
});
