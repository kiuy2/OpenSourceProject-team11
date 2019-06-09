	    $(document).ready(function(){  
            
	        $(".nav-mid-item>a").hover(function() {
                $(this).css("color", "grey");
                $(this).parent().find(".nav-mid-item-drop").slideDown('normal').show(); 
                $(this).parent().find(".nav-mid-item-drop li").hover(function() {
                    $(this).css("background-color", "rgba(255, 255, 255, 0.3)");

                    $(this).mouseleave(function() { 
                        $(this).css("background-color", "");
                    });                    
                });

                $(this).parent().mouseleave(function() { 
                    $(this).find(">a").css("color", "white");
                    $(this).find(".nav-mid-item-drop").slideUp('fast'); 
                });
                
            });  
	    });  