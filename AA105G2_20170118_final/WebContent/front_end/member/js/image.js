function doFirst(){
	document.getElementById('theFile').onchange = fileChange;
}

function fileChange(){
	var file = document.getElementById('theFile').files[0];
	var readFile = new FileReader();
	readFile.readAsDataURL(file);			
	readFile.addEventListener('load',function(){
		var image = document.getElementById('image');
		image.src = readFile.result;
	},false);
}

window.addEventListener('load',doFirst,false);