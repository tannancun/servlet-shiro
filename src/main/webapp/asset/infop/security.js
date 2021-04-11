function loadMenuData() {
    var table = $('#example').DataTable({
        language: {url: 'asset/datatables/Chinese.json'},
        processing: true,
        serverSide: true,
        paging: true,//开启分页
        searching: true,
        lengthMenu: [ //自定义分页长度
            [15, 30, 50, 100],
            ['15 页', '30页', '50页', '100页']
        ],
        order: [[0, "asc"]],
        ajax: {
            url: "console/menu-data.do",
            data: function (data) {
                console.log(data);
            }
        },
        columns: [{data: 'oid', width: '50px'},
            {data: 'levelname',defaultContent: ''},
			{data: 'symbol',defaultContent: ''},
            {
                data: null, searchable: false, orderable: false, width: '120px',
                render: function (data, type, full, meta) {
                    var id = data.id;
                    var url ='console/address.jsp?id='+id;
                    var html = '<div style="text-align: center;"> <a href="#" class="btn btn-primary btn-xs" target="_blank">详情</a> ';
                    html += '<a href="console/menu-edit.jsp?id=' + id + '" class="btn btn-warning btn-xs" target="_blank">修改</a> ';
                    html += '<a href="javascript:void(0)" onclick="openDelteDialog(' + "'" + id + "'" + ')" class="btn btn-danger btn-xs">删除</a> </div>';
                    return html;
                }
            }],
        autoWidth: false

    });
}

function loadMenuLevleName(){
	$.ajax({
        url:"console/menu-level-name.do",
        type:"get",
        success: function(data){
			$("#pid").append("<option value='0'>这是顶级</option>");
			for(var i = 0; i < data.length; i++) {
				$("#pid").append("<option value='" + data[i].id + "'>" + data[i].levelname + "</option>");
			}
        },
        error: function(){}
    });
}


$("#emnu_form").validate({
    rules: {
        name: {
            required: true
        },
        symbol: {
            required: true
        }
    },
    messages: {
        name: {
            required: "请输入菜单名称",
        },
        symbol: {
            required: "请输入符号",
        }
    },
    errorElement: "em",
    errorPlacement: function (error, element) {
        error.addClass("text-danger");
        if (element.prop("type") === "checkbox") {
            error.insertAfter(element.parent("label"));
        } else {
            error.insertAfter(element);
        }
    }
})