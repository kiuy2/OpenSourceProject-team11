$(document).ready(function() {
	$("#input_images").on("change", ImgsFilesSelect);
	$(".upload_images").on("click", ".selFile", removeFile);
});

var sel_files = [];

function removeFile(e) {
	var file = $(this).data("file");
	if(file==null){
		var f=$(this).attr("src");
		for (var i = 0; i < sel_files.length; i++) {
			if (f.indexOf(sel_files[i][1])>=0) {
				sel_files.splice(i, 1);
				break;
			}
		}
	}
	else{
		for (var i = 0; i < sel_files.length; i++) {
			if (sel_files[i].name === file) {
				sel_files.splice(i, 1);
				break;
			}
		}
	}
	$(this).remove();
}
function resetFiles(){
	$(".upload_images").empty();
}

function submitUpdateAction(){
	var form = new FormData();
	var num= frm.num.value;
	for(var i=0, len=sel_files.length; i<len; i++){
		var name= "image_"+i;
		if(sel_files[i][0]=="check")
			form.append(name, sel_files[i][1]);
		else
			form.append(name, sel_files[i]);
	}
	form.append("num", num);
	$.ajax({
	    type : "POST",
	    url : "updateimg.do",
	    data : form,
	    processData: false,
	    contentType: false,
	    success : function(data) {
	    },
	    err : function(err) {
	    }
	});
}

function submitWriteAction(){
	var form = new FormData();
	for(var i=0, len=sel_files.length; i<len; i++){
		var name= "image_"+i;
		form.append(name, sel_files[i]);
	}
	form.append("title", form1.title.value);
	form.append("content", form1.description.value);
	
	$.ajax({
		url : "write.do",
		type : "POST",
		data : form,
	    processData: false,
	    contentType: false,
		success : function(data) {
		},
		error : function(request, status, error) {
		}
	});
}


function setInitial(f) {
	var file=[];
	file[0]="check";
	file[1]=f;
	sel_files.push(file);
}

function ImgsFilesSelect(e) {

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	var index = 0;
	filesArr.forEach(function(f) {
		if (!f.type.match("image.*")) {
			alert("확장자는 이미지 확장자만 가능합니다.");
			$("#input_images").replaceWith($("#input_images").clone(true));
			$("#input_images").val("");
			return;
		}
		sel_files.push(f);
		var reader = new FileReader();
		reader.onload = function(e) {
			var img_html = "<img src='" + e.target.result + "' data-file='"
					+ f.name + "' class='selFile' title='Click to remove'/>";
			$(".upload_images").append(img_html);
			$("#input_images").replaceWith($("#input_images").clone(true));
			$("#input_images").val("");
			index++;
		}
		reader.readAsDataURL(f);
	});
}