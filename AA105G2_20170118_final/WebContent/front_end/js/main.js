// $(document).ready(function() {
//     var num_li = $(".addnav li").length;
//     var img_width = 949;
//     var width_banner = num_li*img_width;
//     var timer = 3000; //  設定輪撥速度
//     $(".banner").css("width", width_banner);

//     //  當滑鼠移過時，圖片移動到特定位置
//     for (i = 0; i < num_li; i++) {
//         $(".addnav li:eq(" + i + ")").mouseenter({
//             id: i
//         }, function(e) {
//             n = e.data.id
//             change();
//         })
//     }

//     //  設定輪撥函數
//     clock = setInterval(auto, timer);
//     n = 0;

//     function auto() {
//         n++;
//         if (n >= num_li) {
//             n = 0;
//         }
//         position = -n * img_width;
//         $(".banner").animate({
//             "left": +position + "px"
//         }, 400)

//         $(".addnav li").css({
//                 "font-weight": "none",
//                 "color": "darkgray",
//                 "font-size": "16px",
//                 "border-bottom": "none"
//             })
//             //  讓滑鼠移到的清單改變成 hover 字體
//         $(".addnav li:eq(" + n + ")").css({
//             "font-weight": "bold",
//             "color": "#0785d4",
//             "font-size": "18px",
//             "border-bottom": "4px solid #07A1D4"
//         })
//     }

//     //  當滑鼠移到圖案上時輪撥暫停
//     $(".banner img").hover(function() {
//         clearInterval(clock)
//     }, function() {
//         clock = setInterval(auto, timer)
//     })

//     //  當滑鼠移到 addnav 時，根據標題移動圖片
//     function change() {
//         clearInterval(clock);
//         clock = setInterval(auto, timer);
//         position2 = -n * img_width;
//         $(".banner").stop();
//         $(".banner").animate({
//                 "left": +position2 + "px"
//             }, 400)
//             //  讓所有的清單變回原本的字體
//         $(".addnav li").css({
//                 "font-weight": "none",
//                 "color": "darkgray",
//                 "font-size": "16px",
//                 "border-bottom": "none"
//             })
//             //  讓滑鼠移到的清單改變成 hover 字體
//         $(".addnav li:eq(" + n + ")").css({
//             "font-weight": "bold",
//             "color": "#0785d4",
//             "font-size": "18px",
//             "border-bottom": "4px solid #07A1D4"
//         })
//     }
// })