function tablePop(obj) {
	window.open("/temp/tableinfo.do?table_num="+$(obj).attr("id"), $(obj).attr("id")+"번 테이블 정보"
			, "width=1000,height=800," +
					"left=50,top50,history=no," +
					"resizable=no,status=no," +
					"scrollbars=no,menubar=no" +
					"location=no");
}

function apppop(obj) {
	location.href="/temp/tableinfo.do?table_num="+$(obj).attr("id");
}