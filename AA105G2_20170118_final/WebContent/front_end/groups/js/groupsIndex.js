$(document).ready(function() {
	$(".groups_select").click(function() {
		var index = $(".groups_select").index(this);
		console.log(index);
		$(".select_form")[index].submit();
	});
	
	$("#createGroupsButton").click(function(){
			$("#errorMsgsArea").remove();
	});
	
	if($("#errorMsgsArea").val() !=null ){
		console.log("test");
		$("#createGroups").modal("show");
	}
	
	$("input[type=button]").click(function(){
		cnosole.log("button test")
	});
});


