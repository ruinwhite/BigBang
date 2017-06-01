/**===============================================================
 * 全局变量
 ===============================================================*/
var husky = {
	pageSize : 5,
	firstPage : 1,
	paginationSize : 5,
	currentPage : 1,
	totalPage : 0
}
/**===============================================================
 * 交互区
 ===============================================================*/
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


/**===============================================================
 * 函数区
 ===============================================================*/
/*
 * 页面初始化
 */
function init(){
	getHotArticles();
	getPageArticles(husky.firstPage,husky.pageSize);
}

/*
 * 获取热点文章
 */
function getHotArticles(){
	$.ajax({
		type: "POST",
		url: "/husky/getHotArticles",
		data: "",
		success: function(data){
			getHotArticlesCallback(data);
		}
	});
}

/*
 * 热点文章获取请求回调函数 
 * @param data
 */
function getHotArticlesCallback(data){
	var obj = jQuery.parseJSON(data);
	if(obj.status==400){
		$("#hot-arts-load").removeClass("alert-info");
		$("#hot-arts-load").addClass("alert-danger");
		$("#hot-arts-load").text(obj.errorInfo);
	}else if(obj.status==200){
		$("#hot-arts-load").addClass("hidden");
		$("#hot-arts-list").removeClass("hidden");
		var lis = $("#hot-arts-list").children("li:not(:first)");
		for(var i in obj.arts){
			createHotArticlesPanel(lis[i],obj.arts[i]);
		}
	}
}

/*
 * 生成热点文章列表
 * @param parent
 * @param art
 */
function createHotArticlesPanel(li,art){
	$(li).val(art.id);
	$(li).children("a").text(art.title);
}

/*
 * 按页码获取数据和页码
 * @param pageNum
 * @param pageSize
 */
function getPageArticles(pageNum,pageSize){
	var json = {"pageNum":pageNum,"pageSize":pageSize};
	$.ajax({
			type:"POST",
			url:"/husky/getPageArticles",
			data:json,
			success: function(data){
				getPageArticlesCallback(data,pageNum,pageSize);
			}
		});
}

/*
 * 文章列表获取成功处理事件
 * @param data
 */
function getPageArticlesCallback(data,pageNum,pageSize){
	var obj = jQuery.parseJSON(data);
	if(obj.status==400){
		$("#arts-list-load").removeClass("alert-info");
		$("#arts-list-load").addClass("alert-danger");
		$("#arts-list-load").text(obj.errorInfo);
	}else if(obj.status==200){
		$("#arts-list-load").addClass("hidden")
		$("#arts-list").children().remove();
		for(var i in obj.arts){
			createPageArticlesPanel($("#arts-list"),obj.arts[i]);
		}
		$("#arts-list").removeClass("hidden");
		createPagePanel(pageNum,obj.page);
		$("#arts-page").removeClass("hidden");
	}
}

/*
 * 生成文章列表
 * @param parent
 * @param art
 */
function createPageArticlesPanel(parent,art){
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

/*
 * 页码条交互处理
 * @param page
 * @param totalPage
 */
function createPagePanel(pageNum,totalPage){
	var halfpaginationSize = parseInt(husky.paginationSize/2);
	$("#btn1").removeClass("active");
	$("#btn2").removeClass("active");
	$("#btn3").removeClass("active");
	$("#btn4").removeClass("active");
	$("#btn5").removeClass("active");
	if(totalPage < husky.paginationSize){
		for(var i=1;i<=husky.paginationSize-totalPage;i++){
			$("#btn"+(husky.paginationSize-i+1)).addClass("hidden");
		}
		for(var i=1;i<=totalPage;i++){
			$("#btn"+i).children("a").text(i);
			$("#btn"+i).val(i);
		}
		$("#btn"+pageNum).addClass("active");
	}else if(totalPage>=husky.paginationSize 
			&& totalPage-pageNum>halfpaginationSize 
			&& pageNum>halfpaginationSize){
		for(var i=1;i<=husky.paginationSize;i++){
			$("#btn"+i).children("a").text(pageNum-halfpaginationSize+i-1);
			$("#btn"+i).val(pageNum-halfpaginationSize+i-1);
			if(i == halfpaginationSize+1){
				$("#btn"+i).addClass("active");
			}
		}
	}else if(totalPage>=husky.paginationSize 
			&& totalPage-pageNum<=halfpaginationSize ){
		for(var i=1;i<=husky.paginationSize;i++){
			$("#btn"+i).children("a").text(totalPage-husky.paginationSize+i);
			$("#btn"+i).val(totalPage-husky.paginationSize+i);
		}
		$("#btn"+(husky.paginationSize-(totalPage-pageNum))).addClass("active");
	}else if(totalPage>=husky.paginationSize 
			&& pageNum<=halfpaginationSize){
		for(var i=1;i<=husky.paginationSize;i++){
			$("#btn"+i).children("a").text(i);
			$("#btn"+i).val(i);
		}
		$("#btn"+pageNum).addClass("active");
	}
	if(pageNum == 1 && pageNum == totalPage){
		$("#preBtn").parent("li").addClass("disabled");
		$("#nextBtn").parent("li").addClass("disabled");
	}else if(pageNum == 1){
		$("#nextBtn").parent("li").removeClass("disabled");
		$("#preBtn").parent("li").addClass("disabled");
	}else if(pageNum == totalPage){
		$("#preBtn").parent("li").removeClass("disabled");
		$("#nextBtn").parent("li").addClass("disabled");
	}else{
		$("#preBtn").parent("li").removeClass("disabled");
		$("#nextBtn").parent("li").removeClass("disabled");
	}
	if(husky.totalPage != totalPage){
		husky.totalPage = totalPage;
	}
	husky.currentPage = pageNum;
}

/*
 * 翻页事件：前一页
 * @returns {Boolean}
 */
function clickPreBtn(obj){
	if($(obj).parent("li").hasClass("disabled")){
		return false;
	}else{
		getPageArticles(husky.currentPage-1,husky.pageSize);
	}
	return false;
}

/*
 *  翻页事件：下一页
 * @returns {Boolean}
 */
function clickNextBtn(obj){
	if($(obj).parent("li").hasClass("disabled")){
		return false;
	}else{
		getPageArticles(husky.currentPage+1,husky.pageSize);
	}
	return false;
}

/*
 *  翻页事件：当前页
 * @returns {Boolean}
 */
function clickCurrentBtn(obj){
	if($(obj).parent("li").hasClass("active")){
		return false;
	}else{
		getPageArticles($(obj).parent("li").val(),husky.pageSize);
	}
	return false;
}

/**===============================================================
 * 初始化
 ===============================================================*/
init();

