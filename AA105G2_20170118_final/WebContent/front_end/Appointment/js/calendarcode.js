

$(document).ready(function() {
	var forbidddenDate;
	var array;
	var xhr = new XMLHttpRequest(); 
	var url= "<%=request.getContextPath()%>/GetDateJason.do?place_no=${param.place_no}";
	xhr.open("Get",url,true); 
	xhr.send( null );
	xhr.onreadystatechange = function (){
	    if( xhr.readyState == 4){
	      if( xhr.status == 200){
	    	  //console.log(xhr.responseText);
	    	  forbidddenDate=JSON.parse(xhr.responseText);
	    	  array=forbidddenDate.forbiddenDate;
	    	  console.log(forbidddenDate.forbiddenDate);

	      }
	      else  alert( xhr.status );
	    }
	 };
			var sty={
			   dayNames:["星期日","星期一","星期二","星期三","星期四","星期五","星期六"],
               dayNamesMin:["日","一","二","三","四","五","六"],
               monthNames:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
                  
               showMonthAfterYear:true,
               dateFormat:"yy-mm-dd" ,
			   numberOfMonths: 2,
			   showButtonPanel: true,
			   minDate: -0,
			   beforeShowDay:notcheck
			   };
			
			   function notcheck(date){


				   	var string = jQuery.datepicker.formatDate('yy-mm-dd',date);
				   	return [array.indexOf(string) ==-1]
			   }
               


			$("#datepicker").datepicker(sty);
		});