var LANGUAGE_Index = "zh_CN"; //标识语言

jQuery(document).ready(function () {
    LANGUAGE_Index = jQuery.i18n.normaliseLanguageCode({}); //获取浏览器的语言
    loadProperties(LANGUAGE_Index);
});

$(".lan_select").change(function () {
    if (($(".lan_select").val() === "英文") || ($(".lan_select").val() === "English")) {
        LANGUAGE_Index = "en_US";
    } else {
        LANGUAGE_Index = "zh_CN";
    }
    loadProperties(LANGUAGE_Index);
});

function loadProperties(type) {
    jQuery.i18n.properties({
        name: 'message', // 资源文件名称
        path: '../properties/', // 资源文件所在目录路径
        mode: 'map', // 模式：变量或 Map
        language: type, // 对应的语言
        cache: false,
        encoding: 'UTF-8',
        callback: function () { // 回调方法
            $('.lan_zh').html($.i18n.prop('lan_zh'));
            $('.lan_en').html($.i18n.prop('lan_en'));
            $('.username').html($.i18n.prop('username'));
            $('.password').html($.i18n.prop('password'));

            $('#isOk').val($.i18n.prop('hello.morning'));
        }
    });
}
