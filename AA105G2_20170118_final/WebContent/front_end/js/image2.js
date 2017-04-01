function doFirst(){
	document.getElementById('theFile2').onchange = fileChange2;
}

function fileChange2(){
	var file = document.getElementById('theFile2').files[0];
	var readFile = new FileReader();
	readFile.readAsDataURL(file);			
	readFile.addEventListener('load',function(){
		var image2 = document.getElementById('image2');
		image2.src = readFile.result;
	},false);
}

window.addEventListener('load',doFirst,false);