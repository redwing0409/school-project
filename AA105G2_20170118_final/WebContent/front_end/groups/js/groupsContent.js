$(document).ready(function() {
	console.log($("#deleteGroupsButton input[type=button]"));
	console.log("讀取完畢");
	$("#deleteGroupsButton input[type=button]").click(function(){
		console.log("刪除社群");
		if(confirm("確定要刪除?")){
			$(this).parent().submit();
		};
	});
});

