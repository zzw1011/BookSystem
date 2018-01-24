Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
var Common = {

    //EasyUI用DataGrid用日期格式化
    TimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }
        var val = dateValue.format("hh:mm");
        return val;
    },
    DateTimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];
        if (dateValue.getFullYear() < 1900) {
            return "";
        }

        return dateValue.format("yyyy-MM-dd hh:mm");
    },

    //EasyUI用DataGrid用日期格式化
    DateFormatter: function (value, rec, index) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        }
        else {
            dt = new Date(value);
            if (isNaN(dt)) {
                value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
                dt = new Date();
                dt.setTime(value);
            }
        }

        if (dt.format("yyyy-MM-dd") == '1900-01-01')
            return ''
        else
            return dt.format("yyyy-MM-dd");  //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
    },
    ImgFormatter: function (val, row, index) {
        var temp = '';
        var path = '/Images/Lesson/';
        if (val != null && val != '') {
            var pathArray = val.split(',');
            for (var i = 0; i < pathArray.length; i++) {
                var text = pathArray[i].substring(pathArray[i].indexOf('_') + 1, pathArray[i].indexOf('_') + 2);
                text = text == '0' ? '图' : '图' + text;
                temp += '<a title="点击查看" target="_blank" href="' + path + pathArray[i] + '">' + text + '<a/>';
                if (i < pathArray.length - 1)
                    temp += '、';
            }
        }
        return temp;
    }
};
$.extend($.fn.validatebox.defaults.rules, {
    selectValueRequired: {
        validator: function (value, param) {
            return $(param[0]).find("option:contains('" + value + "')").val() != '';
        },
        message: 'select value required.'
    }, loginName: {
        validator: function (value, param) {
            if (value.length < param[0]) {
                $.fn.validatebox.defaults.rules.loginName.message = '用户名要至少' + param[0] + '位数！';
                return false;
            } else if (value.length > param[1]) {
                $.fn.validatebox.defaults.rules.loginName.message = '用户名要不能大于' + param[1] + '位数！';
                return false;
            }
            else {
                if (!/^[a-zA-Z][a-zA-Z0-9_]/i.test(value)) {
                    $.fn.validatebox.defaults.rules.loginName.message = '用户名不合法（字母开头，允许字母数字下划线）！';
                    return false;
                } else {
                    return true;
                }
            }
        }
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    }
});

$.extend($.fn.validatebox.methods, {
    remove: function (jq, newposition) {
        return jq.each(function () {
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
        });
    },
    reduce: function (jq, newposition) {
        return jq.each(function () {
            var opt = $(this).data().validatebox.options;
            $(this).addClass("validatebox-text").validatebox(opt);
        });
    }
});
String.prototype.removeLineEnd = function () {
    return this.replace(/(<.+?\s+?)(?:\n\s*?(.+?=".*?"))/g, '$1 $2')
}
function formatXml(text) {
    //去掉多余的空格
    text = '\n' + text.replace(/(<\w+)(\s.*?>)/g, function ($0, name, props) {
            return name + ' ' + props.replace(/\s+(\w+=)/g, " $1");
        }).replace(/>\s*?</g, ">\n<");

    //把注释编码
    text = text.replace(/\n/g, '\r').replace(/<!--(.+?)-->/g, function ($0, text) {
        var ret = '<!--' + escape(text) + '-->';
        //alert(ret);
        return ret;
    }).replace(/\r/g, '\n');

    //调整格式
    var rgx = /\n(<(([^\?]).+?)(?:\s|\s*?>|\s*?(\/)>)(?:.*?(?:(?:(\/)>)|(?:<(\/)\2>)))?)/mg;
    var nodeStack = [];
    var output = text.replace(rgx, function ($0, all, name, isBegin, isCloseFull1, isCloseFull2, isFull1, isFull2) {
        var isClosed = (isCloseFull1 == '/') || (isCloseFull2 == '/' ) || (isFull1 == '/') || (isFull2 == '/');
        //alert([all,isClosed].join('='));
        var prefix = '';
        if (isBegin == '!') {
            prefix = getPrefix(nodeStack.length);
        }
        else {
            if (isBegin != '/') {
                prefix = getPrefix(nodeStack.length);
                if (!isClosed) {
                    nodeStack.push(name);
                }
            }
            else {
                nodeStack.pop();
                prefix = getPrefix(nodeStack.length);
            }


        }
        var ret = '\n' + prefix + all;
        return ret;
    });

    var prefixSpace = -1;
    var outputText = output.substring(1);
    //alert(outputText);

    //把注释还原并解码，调格式
    outputText = outputText.replace(/\n/g, '\r').replace(/(\s*)<!--(.+?)-->/g, function ($0, prefix, text) {
        //alert(['[',prefix,']=',prefix.length].join(''));
        if (prefix.charAt(0) == '\r')
            prefix = prefix.substring(1);
        text = unescape(text).replace(/\r/g, '\n');
        var ret = '\n' + prefix + '<!--' + text.replace(/^\s*/mg, prefix) + '-->';
        //alert(ret);
        return ret;
    });

    return outputText.replace(/\s+$/g, '').replace(/\r/g, '\r\n');

}

function getPrefix(prefixIndex) {
    var span = '    ';
    var output = [];
    for (var i = 0; i < prefixIndex; ++i) {
        output.push(span);
    }

    return output.join('');
}
function formatJson(txt, compress/*是否为压缩模式*/) {/* 格式化JSON源码(对象转换为JSON文本) */
    var indentChar = '&nbsp;&nbsp;&nbsp;';
    if (/^\s*$/.test(txt)) {
        alert('数据为空,无法格式化! ');
        return;
    }
    try {
        var data = eval('(' + txt + ')');
    }
    catch (e) {
        alert('数据源语法错误,格式化失败! 错误信息: ' + e.description, 'err');
        return;
    }
    ;
    var draw = [], last = false, This = this, line = compress ? '' : '<br/>', nodeCount = 0, maxDepth = 0;

    var notify = function (name, value, isLast, indent/*缩进*/, formObj) {
        nodeCount++;
        /*节点计数*/
        for (var i = 0, tab = ''; i < indent; i++)tab += indentChar;
        /* 缩进HTML */
        tab = compress ? '' : tab;
        /*压缩模式忽略缩进*/
        maxDepth = ++indent;
        /*缩进递增并记录*/
        if (value && value.constructor == Array) {/*处理数组*/
            draw.push(tab + (formObj ? ('"' + name + '":') : '') + '[' + line);
            /*缩进'[' 然后换行*/
            for (var i = 0; i < value.length; i++)
                notify(i, value[i], i == value.length - 1, indent, false);
            draw.push(tab + ']' + (isLast ? line : (',' + line)));
            /*缩进']'换行,若非尾元素则添加逗号*/
        } else if (value && typeof value == 'object') {/*处理对象*/
            draw.push(tab + (formObj ? ('"' + name + '":') : '') + '{' + line);
            /*缩进'{' 然后换行*/
            var len = 0, i = 0;
            for (var key in value)len++;
            for (var key in value)notify(key, value[key], ++i == len, indent, true);
            draw.push(tab + '}' + (isLast ? line : (',' + line)));
            /*缩进'}'换行,若非尾元素则添加逗号*/
        } else {
            if (typeof value == 'string')value = '"' + value + '"';
            draw.push(tab + (formObj ? ('"' + name + '":') : '') + value + (isLast ? '' : ',') + line);
        }
        ;
    };
    var isLast = true, indent = 0;
    notify('', data, isLast, indent, false);
    return draw.join('');
}

function frmAuditAndEditAndDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action exam" onclick="auditDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action look" onclick="viewDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}


function formatEdit(val, row, index) {
    return '<a href="#" class="action operate" onclick="editDatagrid(' + index + ')"/>';
}
function formatEditAndDel(val, row, index) {
    return '<a href="#" class="action operate" onclick="editDatagrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="#" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function formatViewAndDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action look" onclick="viewDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function formatAuditAndEditAndDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action operate" onclick="editDatagrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action exam" onclick="auditDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function formatEditAndDelAndView(val, row, index) {
    return '<a href="javascript:void(0)" class="action operate" onclick="editDatagrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action look" onclick="viewDategrid(' + index + ')"/>';
}

function formatEditAndView(val, row, index) {
    return '<a href="javascript:void(0)" class="action operate" onclick="editDatagrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action look" onclick="viewDategrid(' + index + ')"/>';
}
function formatDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function formatView(val, row, index) {
    return '<a href="javascript:void(0)" class="action look" onclick="viewDategrid(' + index + ')"/>';
}
function formatEdit(val, row, index) {
    return '<a href="#" class="action operate" onclick="editDatagrid(' + index + ')"/>';
}

function formatPinAndDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action exam" onclick="reDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="#" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function formatEditPinAndDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action operate" onclick="editDatagrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action exam" onclick="reDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="#" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function  formatPin(val,row,index)
{
    return '<a href="javascript:void(0)" class="action exam" onclick="reDategrid('+index+')"/>';
}

function formatAuditAndViewAndDel(val, row, index) {
    if (row.cState == 0 || row.cState == null) {
        return '<a href="javascript:void(0)" class="action exam" onclick="auditDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
    }
    if (row.cState == 1 || row.cState == 2) {
        return '<a href="javascript:void(0)" class="action look" onclick="viewDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="javascript:void(0)" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
    }
}

function formatPinAndEditAndDel(val, row, index) {
    return '<a href="javascript:void(0)" class="action exam" onclick="reDategrid(' + index + ')"/>' + '&nbsp;&nbsp;<a href="#" class="action delete" onclick="deleteDategrid(' + index + ')"/>';
}

function randomString(len) {
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}
