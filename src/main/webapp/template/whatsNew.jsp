<%@ page contentType="text/html" pageEncoding="utf-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="edu.mssm.pharm.maayanlab.Harmonizome.util.Constant" %>


<t:wrapper title="What's New in Harmonizome 3.0?">
    <jsp:body>
        <div class="whats-new-page">
            <h1>What's New in Harmonizome 3.0?</h1>
            <section>
                <p><h2>New Datasets</h2></p>
                <p><h3>Disease or Phenotype Associations</h3></p>
                <ul>
                    <li><a href="dataset/DepMap+CRISPR+Gene+Dependency">DepMap CRISPR Gene Dependency</a></li>
                    <ul>
                        <li>Dependency scores for cell lines following single gene knockdowns</li>
                        <li>15,946 genes, 1095 cell lines, 697,098 associations</li>
                    </ul>
                    <li><a href="dataset/DisGeNET+Gene-Disease+Associations">DisGeNET Gene-Disease Associations</a></li>
                    <ul>
                        <li>Gene-disease associations sourced from curated repositories, GWAS catalogues, animal models and the scientific literature</li>
                        <li>15,960 genes, 15,709 diseases, 652,358 associations</li>
                    </ul>
                    <li><a href="dataset/DisGeNET+Gene-Phenotype+Associations">DisGeNET Gene-Phenotype Associations</a></li>
                    <ul>
                        <li>Gene-phenotype associations sourced from curated repositories, GWAS catalogues, animal models and the scientific literature</li>
                        <li>14,002 genes, 6,832 phenotypes, 196,561 associations</li>
                    </ul>
                    <li><a href="dataset/IMPC+Knockout+Mouse+Phenotypes">IMPC Knockout Mouse Phenotypes</a></li>
                    <ul>
                        <li>Observed phenotypes of mice following gene knockout</li>
                        <li>6,769 genes, 667 phenotypes, 36,450 associations</li>
                    </ul>
                    <li><a href="dataset/MGI+Mouse+Phenotype+Associations+2023">MGI Mouse Phenotype Associations 2023</a></li>
                    <ul>
                        <li>Observed phenotypes of transgenic mice collected from mouse phenotyping studies, updated for 2023</li>
                        <li>15,572 genes, 10,295 phenotypes, 265,193 associations</li>
                    </ul>
                </ul>
                <p><h3>Genomics</h3></p>
                <ul>
                    <li><a href="dataset/CHEA+Transcription+Factor+Binding+Site+Profiles+2022">ChEA Transcription Factor Binding Site Profiles 2022</a></li>
                    <ul>
                        <li>Transcription factor binding site profiles from published ChIP-chip, ChIP-seq, and other transcription factor functional studies, updated for 2022</li>
                        <li>18,365 genes, 757 transcription factor binding site profiles, 917,047 associations</li>
                    </ul>
                </ul>
                <p><h3>Physical Interactions</h3></p>
                <ul>
                    <li><a href="dataset/MW+Gene+Metabolite+Associations">Metabolomics Workbench Gene Metabolite Associations</a></li>
                    <ul>
                        <li>Biomolecular interactions between metabolites and proteins curated from experimental studies</li>
                        <li>1,050 genes, 734 metabolites, 4,092 associations</li>
                    </ul>
                </ul>
                <p><h3>Proteomics</h3></p>
                <ul>
                    <li><a href="dataset/Cancer+Cell+Line+Encyclopedia+Cell+Line+Proteomics">Cancer Cell Line Encyclopedia Cell Line Proteomics</a></li>
                    <ul>
                        <li>Protein intensity values acquired using mass spectrometry across human cancerous cell lines</li>
                        <li>8,959, genes, 375 cell lines, 122,408 associations</li>
                    </ul>
                    <li><a href="dataset/DeepCoverMOA+Drug+Mechanisms+of+Action">DeepCoverMOA Drug Mechanisms of Action</a></li>
                    <ul>
                        <li>Protein expression profiles in HCT116 cell line following drug treatment</li>
                        <li>8,405 genes, 874 small molecules, 260,471 associations</li>
                    </ul>
                    <li><a href="dataset/Glygen+Glycosylated+Proteins">Glygen Glycosylated Proteins</a></li>
                    <ul>
                        <li>Proteins glycosylated by saccharide ligands from glycosylation site citations</li>
                        <li>1,910 genes, 2,231 glycans, 20,486 associations</li>
                    </ul>
                    <li><a href="dataset/Kinase+Library+Serine+Threonine+Kinome+Atlas">Kinase Library Serine Threonine Kinase Atlas</a></li>
                    <ul>
                        <li>A phosphoproteomics atlas detailing phosphorylation of protein substrates by 303 serine/threonine kinases in the human kinome</li>
                        <li>5,046 genes, 303 kinases, 30,300 associations</li>
                    </ul>
                </ul>
                <p><h3>Structural or Functional Annotations</h3></h4></p>
                <ul>
                    <li><a href="dataset/GO+Biological+Process+Annotations+2023">Gene Ontology Biological Process Annotations 2023</a></li>
                    <ul>
                        <li>Curated annotations of genes with biological processes from the Gene Ontology, updated for 2023</li>
                        <li>14,811 genes, 12,318 biological processes, 198,050 associations</li>
                    </ul>
                    <li><a href="dataset/GO+Cellular+Component+Annotations+2023">Gene Ontology Cellular Component Annotations 2023</a></li>
                    <ul>
                        <li>Curated annotations of genes with cellular components from the Gene Ontology, updated for 2023</li>
                        <li>11,089 genes, 926 cellular componenets, 41,883 associations</li>
                    </ul>
                    <li><a href="dataset/GO+Molecular+Function+Annotations+2023">Gene Ontology Molecular Function Annotations 2023</a></li>
                    <ul>
                        <li>Curated annotations of genes with molecular functions from the Gene Ontology, updated for 2023</li>
                        <li>12,478 genes, 3,851 molecular functions, 50,339 associations</li>
                    </ul>
                    <li><a href="dataset/Wikipathways+PFOCR">Wikipathways PFOCR</a></li>
                    <ul>
                        <li>Sets of genes extracted from pathway figure using optical character recognition</li>
                        <li>13,173 genes, 35,464 pathways, 307,416 associations</li>
                    </ul>
                </ul>
                <p><h3>Transcriptomics</h3></p>
                <ul>
                    <li><a href="dataset/GTEx+Tissue-Specific+Aging+Signatures">GTEx Tissue-Specific Aging Signatures</a></li>
                    <ul>
                        <li>Tissue-specific aging signatures createed from GTEx RNA-seq gene expression profiles</li>
                        <li>16,047 genes, 135 tissue samples, 67,500 associations</li>
                    </ul>
                    <li><a href="dataset/GTEx+Tissue+Gene+Expression+Profiles+2023">GTEx Tissue Gene Expression Profiles 2023</a></li>
                    <ul>
                        <li>Gene expression profiles for tissues from GEx by RNA-seq, updated for 2023</li>
                        <li>17,369 genes, 54 tissues, 108,000 associations</li>
                    </ul>
                    <li><a href="dataset/HuBMAP+Azimuth+Cell+Type+annotations">HuBMAP Azimuth Cell Type Annotations</a></li>
                    <ul>
                        <li>Gene-cell type annotations from integrated reference scRNA-seq gene expression profiles</li>
                        <li>3,560 genes, 1,426 cell types, 13,757 associations</li>
                    </ul>
                    <li><a href="dataset/KnockTF+Gene+Expression+Profiles+with+Transcription+Factor+Perturbations">KnockTF Gene Expression Profiles with Transcription Factor Perturbations</a></li>
                    <ul>
                        <li>Gene expression profiles for cell lines or tissues following transcription factor perturbation (knockdown/knockout)</li>
                        <li>17,964 genes, 566 transcription factor perturbations, 105,586 associations</li>
                    </ul>
                    <li><a href="dataset/LINCS+L1000+CMAP+Chemical+Perturbation+Consensus+Signatures">LINCS L1000 Connectivity Map Chemical Perturbation Consensus Signatures</a></li>
                    <ul>
                        <li>Gene association consensus signatures following chemical perturbation</li>
                        <li>12,126 genes, 23,912 small molecule perturbations, 5,086,167 associations</li>
                    </ul>
                    <li><a href="dataset/LINCS+L1000+CMAP+CRISPR+Knockout+Consensus+Signatures">LINCS L1000 Connectivity Map CRISPR Knockout Consensus Signatures</a></li>
                    <ul>
                        <li>Gene association consensus signatures following CRISPR gene knockout</li>
                        <li>9,551 genes, 5,049 gene perturbations, 2,517,262 associations</li>
                    </ul>
                    <li><a href="dataset/Sanger+Dependency+Map+Cancer+Cell+Line+Proteomics">Sanger Cancer Dependency Map Cancer Cell Line Proteomics</a></li>
                    <ul>
                        <li>Protein intensity values acquired using data-independent acquisition mass spectrometry (DIA-MS)</li>
                        <li>8,087 genes, 949 cell lines, 186,983 associations</li>
                    </ul>
                    <li><a href="dataset/Tabula+Sapiens+Gene-Cell+Associations">Tabula Sapiens Gene-Cell Associations</a></li>
                    <ul>
                        <li>Gene expression counts for single human cells</li>
                        <li>8,184 genes, 469 cell types, 46,900 associations</li>
                    </ul>
                </ul>
            </section>
            <section>
                <p><h2>New Download Type</h2></p>
                <p><h4>Knowledge Graph Serializations</h4></p>
                <p>A compressed folder containing serialized dataset associations ready for knowledge graph ingestion in multiple formats:</p>
                <ul>
                    <li>RDF</li>
                    <pre><code class="rdf">
                        <b>gobp23.rdf</b>
                        @prefix gene: ncbi.nlm.nih.gov/gene/
                        @prefix RO: purl.obolibrary.org/RO_
                        @prefix GO: amigo.geneontology.org/amigo/term/GO:

                        gene:28655 RO:0000056 GO:0050830 .
                        gene:728637 RO:0000056 GO:0010789 .
                        gene:728637 RO:0000056 GO:0045143 .
                        gene:728637 RO:0000056 GO:0051754 .
                        gene:1564 RO:0000056 GO:0042178 .
                        gene:101928527 RO:0000056 GO:1900101 .
                        gene:100130520 RO:0000056 GO:0030593 .
                        gene:550643 RO:0000056 GO:0000956 .
                        gene:550643 RO:0000056 GO:0010607 .
                        gene:100507043 RO:0000056 GO:0032469 .
                        gene:100507043 RO:0000056 GO:0035774 .
                        gene:100507043 RO:0000056 GO:0045665 .
                        gene:100507043 RO:0000056 GO:0048812 .
                        gene:100507043 RO:0000056 GO:0051480 .
                        gene:101929726 RO:0000056 GO:0007520 .
                        gene:101929726 RO:0000056 GO:0014905 .
                        ...
                    </code></pre>
                    <li>JSON</li>
                    <pre><code class="json ">
                        <b>gobp23.json</b>
                        "Version": "1",
                            "nodes": {
                                "28655": {
                                    "type": "gene",
                                    "properties": {
                                        "id": 28655,
                                        "label": "TRAV27"
                                    }
                                },
                                ...,
                                "GO:0002222": {
                                    "type": "biological process",
                                    "properties": {
                                        "id": "GO:0002222",
                                        "label": "stimulatory killer cell immunoglobulin-like receptor signaling pathway"
                                    }
                                }}
                            "edges": [
                                {
                                    "source": 28655,
                                    "relation": "participates in",
                                    "target": "GO:0050830",
                                    "properties": {
                                        "id": "28655:GO:0050830",
                                        "source_id": 28655,
                                        "source_label": "TRAV27",
                                        "target_label": "defense response to Gram-positive bacterium",
                                        "target_id": "GO:0050830",
                                        "directed": true,
                                        "threshold": 1
                                    }
                                },
                                ...,
                                {
                                    "source": 728637,
                                    "relation": "participates in",
                                    "target": "GO:0010789",
                                    "properties": {
                                        "id": "728637:GO:0010789",
                                        "source_id": 728637,
                                        "source_label": "MEIKIN",
                                        "target_label": "meiotic sister chromatid cohesion involved in meiosis I",
                                        "target_id": "GO:0010789",
                                        "directed": true,
                                        "threshold": 1
                                    }
                                }
                            ]
                    </code></pre>
                    <li>TSV</li>
                        <div class="row">
                            <div class="row">
                                <pre><code><b>gobp23_tsv/nodes.tsv</b></code></pre>
                                <table id="nodes" class="table">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>namespace</th>
                                            <th>id</th>
                                            <th>label</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>0</td>
                                            <td>NCBI Entrez</td>
                                            <td>28655</td>
                                            <td>TRAV27</td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>NCBI Entrez</td>
                                            <td>728637</td>
                                            <td>MEIKIN</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>NCBI Entrez</td>
                                            <td>1564</td>
                                            <td>CYP2D7</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>NCBI Entrez</td>
                                            <td>101928527</td>
                                            <td>PIGBOS1</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>NCBI Entrez</td>
                                            <td>100130520</td>
                                            <td>CD300H</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                                <pre><code><b>gobp23_tsv/edges.tsv</b></code></pre>
                                <table id="edges" class="table">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>source</th>
                                            <th>relation</th>
                                            <th>target</th>
                                            <th>threshold</th>
                                        </tr>
                                        </thead>
                                    <tbody>
                                        <tr>
                                            <td>0</td>
                                            <td>28655</td>
                                            <td>participates in</td>
                                            <td>GO:0050830</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>728637</td>
                                            <td>participates in</td>
                                            <td>GO:0010789</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>728637</td>
                                            <td>participates in</td>
                                            <td>GO:0045143</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>3</td>
                                            <td>728637</td>
                                            <td>participates in</td>
                                            <td>GO:0051754</td>
                                            <td>1</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>1564</td>
                                            <td>participates in</td>
                                            <td>GO:0042178</td>
                                            <td>1</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>        
                        </div>
                </ul>
            </section>
            <section>
                <p><h2>New Visualizations</h2></p>
                <div class="row">
                    <p><h4><a href="visualize/heat_map/dataset_pairs">Dataset Pair Visualizations</a></h4></p>
                    <ul>
                        <li>Hierarchically clustered heat maps to compare the similarity of attributes from two datasets</li>
                        <li>Added over 1,000 new dataset pair attribute similarity heat maps</li>
                    </ul>
                    <iframe src="https://amp.pharm.mssm.edu/clustergrammer/viz/64592cb6b1ed870010384a4d?row_label=Cell types&col_label=Diseases" scrolling="no" frameBorder="0" width="1000px" height="700px"></iframe>
                    <p><h4><a href="visualize/umap">UMAP</a></h4></p>
                    <ul>
                        <li>Interactive cluster plots created using TF-IDF vectorization of gene sets</li>
                    </ul>
                    <iframe src="https://amp.pharm.mssm.edu/static/harmonizome/heat_maps/umap/deepcovermoa.html" scrolling="no" frameBorder="0" width="1000px" height="700px"></iframe>
                </div>
            </section>
        </div>
    </jsp:body>
</t:wrapper>