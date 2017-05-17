$("header div.navbar ul.nav li").hover(function(){
	$(this).addClass("bg-info");
},function(){
	$("header div.navbar ul.nav li").removeClass("bg-info");
});

$("header div.navbar ul.nav li:not(.dropdown)").click(function(){
	$("header div.navbar ul.nav li:not(.dropdown)").removeClass("active");
	$(this).addClass("active");
});

$("header div.navbar ul.nav li.dropdown").click(function(){
	$("header div.navbar ul.nav li").removeClass("active");
});

function getPageArticles(pageNum,pageSize){
	var json = {"pageNum":pageNum,"pageSize":pageSize};
	$.post("/husky/getPageArticles",json,function(data){
		var obj = jQuery.parseJSON(data);
		if(obj.status==400){
			$("#arts-list").children().remove();
			$("#arts-list").addClass("text-center");
			$("#arts-list").addClass("alert alert-danger");
			$("#arts-list").text(obj.errorInfo);
		}else if(obj.status==200){
			$("#arts-list").children().remove();
			for(var i in obj.arts){
				createPanel($("#arts-list"),obj.arts[i]);
			}
		}
	});
}

function createPanel(parent,art){
	var div = $('<div id="p4" class="panel panel-default"></div>');
	var headDiv = $('<div class="panel-heading"></div>');
	var title = $('<h3 class="panel-title"></h3>');
	title.text(art.title);
	headDiv.append(title);
	div.append(headDiv);
	var bodyDiv = $('<div class="panel-body"></div>');
	bodyDiv.text(art.content);
	div.append(bodyDiv);
	parent.append(div);
}

$(function(){
	getPageArticles(1,5);
})
