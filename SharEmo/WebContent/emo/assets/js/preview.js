$(document).ready(function() {
	$("#input_images").on("change", ImgsFilesSelect);
	$(".upload_images").on("click", ".selFile", removeFile);
});

var sel_files = [];

function removeFile(e) {
	var file = $(this).data("file");
	for(var i=0;i<sel_files.length;i++) {
		if(sel_files[i].name === file) {
			sel_files.splice(i,1);
			file.val("");
			break;
		}
	}
	$(this).remove();
}

function ImgsFilesSelect(e) {
	$(".upload_images").empty();

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	var index = 0;
	filesArr.forEach(function(f) {
				if (!f.type.match("image.*")) {
					alert("확장자는 이미지 확장자만 가능합니다.");
					return;
				}
				sel_files.push(f);

				var reader = new FileReader();
				reader.onload = function(e) {
					var img_html = "<img src='"
							+ e.target.result
							+ "' data-file='"
							+ f.name + "' class='selFile' title='Click to remove'/>";
					$(".upload_images").append(img_html);
					index++;
				}
				reader.readAsDataURL(f);
			});
}