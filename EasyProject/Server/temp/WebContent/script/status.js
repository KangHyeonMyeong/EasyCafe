setInterval(function() { $.post("/temp/view/cafestatus/seatdata.jsp",
		{},
		function(data,status){//응답내용 처리
			if(status == "success"){//요청이 제대로 처리되었으면
				$(".result").html(data);
			}else if(status =="error"){
				alert("에러뜸");
			}
		}); }, 1000);