$(document).ready(()=>{
        $('.lattice').click(function(){
            var index = $(this).attr('data-index');
            sessionStorage.setItem('index',index);
            window.open('vr.html');
        });
        $(document).ready(function () {
            $('.bar_content').click(function () {
                    console.log($(this).index());
                    if($(this).index()===0){
                        window.location.href = 'Main.html';
                    }else if($(this).index()===1){
                        window.location.href = 'storehouse.html';
                    }else if($(this).index()===4){
                        window.location.href = 'message.html';
                    }
                });
        });
})