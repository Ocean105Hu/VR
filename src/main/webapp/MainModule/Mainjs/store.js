$(document).ready(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const username = urlParams.get('username');
    if (username != null) {
        $('.nav_login a').text(username);
    }
    $('.lattice').click(function () {
        var index = $(this).attr('data-index');
        sessionStorage.setItem('index', index);
        window.open('vr.html');
    });
    $('.bar_content').click(function () {
        console.log($(this).index());
        if ($(this).index() === 0) {
            window.location.href = `Main.html?username=${encodeURIComponent(username)}`;
        } else if ($(this).index() === 1) {
            window.location.href = 'storehouse.html';
        } else if ($(this).index() === 4) {
            window.location.href = 'message.html';
        }
    });
    const arr = [
        {text:"台湾",author:"中国台湾"},
        {text:"北京-八达岭",author:"爱吃哈密瓜"}
    ]
    let preindex = ($('.lattice').last().index())+1;
    console.log(preindex);
    let insertdiv =(inedx,text,author)=>`<div class="lattice" data-index="1-${index}">
                <a class="card" target="_blank">
                    <div class="wrapper-img">
                        <img class="changeImgSize" src="./img/vr_img/1-${index}/thumb.jpg" alt="马来西亚-吉隆坡VR全景">
                    </div>
                    <div class="lattice-name">${text}</div>
                    <div class="card-actions">
                        <div class="pull-left">
                            <img style="width: 28px; height:28px; overflow:hidden; " src="img/vr_img/icon.png" alt="">
                            <span class="rephoto-author">${author}</span>
                        </div>
                        <div class="pull-right">
                            <img src="img/vr_img/see.png" style="width: 18px;height: 12px;display: inline-block;margin-bottom: 3px;margin-right:5px" alt="">
                            72457
                        </div>
                    </div>
                </a>
            </div>`;
    let imagePath = `img/vr_img/1-${preindex}/pano_b.jpg`;

    let img = new Image();
    img.src = imagePath;

    img.onload = function() {
        arr.forEach((i)=>{
            $('pictures_content').append(insertdiv(preindex,i.text,i.author))
        });
    };

})