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
