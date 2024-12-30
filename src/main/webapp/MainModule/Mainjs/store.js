$(document).ready(()=>{
        $('.lattice').click(function(){
            var index = $(this).attr('data-index');
            sessionStorage.setItem('index',index);
            window.open('vr.html');
    });
})