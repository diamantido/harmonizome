<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>


<t:wrapper title="What's New in Harmonizome 3.0?">
    <jsp:body>
        <div class="whats-new-page">
            <div class="row">
                <h1>What's New in Harmonizome 3.0?</h1>
            </div>
            <div class="row">
                <p><h2>New Datasets</h2></p>
                <p><h3>June 13th, 2023</h3></p>
                <ul>
                    <li><a href="dataset/Gene+Ontology+Biological+Process+Annotations+2023">Gene Ontology Biological Process Annotations 2023</a></li>
                    <ul>
                        <li>Curated annotations of genes with biological processes from the Gene Ontology, updated for 2023</li>
                    </ul>
                    <li><a href="dataset/Gene+Ontology+Cellular+Component+Annotations+2023">Gene Ontology Cellular Component Annotations 2023</a></li>
                    <ul>
                        <li>Curated annotations of genes with cellular components from the Gene Ontology, updated for 2023</li>
                    </ul>
                    <li><a href="dataset/Gene+Ontology+Molecular+Function+Annotations+2023">Gene Ontology Molecular Function Annotations 2023</a></li>
                    <ul>
                        <li>Curated annotations of genes with molecular functions from the Gene Ontology, updated for 2023</li>
                    </ul>
                    <li><a href="dataset/Wikipathways+PFOCR">Wikipathways PFOCR</a></li>
                    <ul>
                        <li>Sets of genes extracted from pathway figure using optical character recognition</li>
                    </ul>
                </ul>
                <p><h3>May 12th, 2023</h3></p>
                <ul>
                    <li><a href="dataset/DeepCoverMOA+Drug+Mechanisms+of+Action">DeepCoverMOA Drug Mechanisms of Action</a></li>
                    <ul>
                        <li>Protein expression profiles in HCT116 cell line following drug treatment</li>
                    </ul>
                    <li><a href="dataset/DisGeNET+Gene-Disease+Associations">DisGeNET Gene-Disease Associations</a></li>
                    <ul>
                        <li>Gene-disease associations sourced from curated repositories, GWAS catalogues, animal models and the scientific literature</li>
                    </ul>
                    <li><a href="dataset/DisGeNET+Gene-Phenotype+Associations">DisGeNET Gene-Phenotype Associations</a></li>
                    <ul>
                        <li>Gene-phenotype associations sourced from curated repositories, GWAS catalogues, animal models and the scientific literature</li>
                    </ul>
                    <li><a href="dataset/Kinase+Library+Serine+Threonine+Kinome+Atlas">Kinase Library Serine Threonine Kinase Atlas</a></li>
                    <ul>
                        <li>A phosphoproteomics atlas detailing phosphorylation of protein substrates by 303 serine/threonine kinases in the human kinome</li>
                    </ul>
                    <li><a href="dataset/KnockTF+Gene+Expression+Profiles+with+Transcription+Factor+Perturbations">KnockTF Gene Expression Profiles with Transcription Factor Perturbations</a></li>
                    <ul>
                        <li>Gene expression profiles for cell lines or tissues following transcription factor perturbation (knockdown/knockout)</li>
                    </ul>
                    <li><a href="dataset/Sanger+Dependency+Map+Cancer+Cell+Line+Proteomics">Sanger Cancer Dependency Map Cancer Cell Line Proteomics</a></li>
                    <ul>
                        <li>Protein intensity values acquired using data-independent acquisition mass spectrometry (DIA-MS)</li>
                    </ul>
                    <li><a href="dataset/Tabula+Sapiens+Gene-Cell+Associations">Tabula Sapiens Gene-Cell Associations</a></li>
                    <ul>
                        <li>Gene expression counts for single human cells</li>
                    </ul>
                </ul>
            </div>
            <div class="row">
                <p><h2>New Download Type</h2></p>
                <p><h4>Knowledge Graph Serializations</h4></p>
                <p>A compressed folder containing serialized dataset associations ready for knowledge graph ingestion in multiple formats:</p>
                <ul>
                    <li>RDF</li>
                    <li>JSON</li>
                    <li>TSV</li>
                </ul>
            </div>
            <div class="row">
                <p><h2>New Visualizations</h2></p>
                <div class="row">
                    <p><h4><a href="visualize/heat_map/dataset_pairs">Dataset Pair Visualizations</a></h4></p>
                    <ul>
                        <li>Added over 1,000 new dataset pair attribute similarity heat maps</li>
                    </ul>
                    <p><h4><a href="visualize/heat_map/umap">UMAP</a></h4></p>
                    <ul>
                        <li>Interactive cluster plots</li>
                    </ul>
                    <iframe src="https://amp.pharm.mssm.edu/static/harmonizome/heat_maps/umap/deepcovermoa.html" scrolling="no" frameBorder="0" width="1003px" height="703px"></iframe>
                </div>
            </div>
        </div>
    </jsp:body>
</t:wrapper>