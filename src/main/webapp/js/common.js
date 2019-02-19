$(document).ready(function () {
    //search  
  $(".searchico").click(function(){
  $(".search").toggleClass("open");
  });
  
   //searchclose  
    $(".searchclose").click(function(){
  $(".search").removeClass("open");
  });
});