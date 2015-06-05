<div class="filter-engine">
	<h3>Filters</h3>
	<div class="row">
		<div class="col-sm-4 <%= request.getAttribute("pageType") == "dataset" ? "locked" : "" %>">
			<select>
				<option>Dataset</option>
			</select>
			<br>
			<select>
				<option>Dataset Group</option>
			</select>
			<br>
			<select>
				<option>Dataset Type</option>
			</select>
		</div>
		<div class="col-sm-4 <%= request.getAttribute("pageType") == "attribute" ? "locked" : "" %>">
			<select>
				<option>Attribute Group</option>
			</select>
			<br>
			<select>
				<option>Attribute Type</option>
			</select>
		</div>
		
		<div class="col-sm-4 <%= request.getAttribute("pageType") == "gene" ? "locked" : "" %>">
			<input type="text">
			<br>
			<input type="text">
		</div>
	</div>
	<button>Filter</button>
</div>