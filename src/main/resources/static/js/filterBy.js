$( document ).ready(function() {

    // GET REQUEST
    $("#filterr").change(function(event){
        event.preventDefault();
        $("#resultDiv").empty();
        $("#theadd").empty();

        let filterCriteria = $(this).find(":selected").val();


        ajaxGet(filterCriteria);

    });

    // DO GET
    function ajaxGet(filterCriteria){
        $.ajax({
            type : "GET",
            url : "http://localhost:8080/api/v1/user/task/taskUnit/filterBy/"+filterCriteria,
            success: function(result){
                if(result.status === "OK"){
                    $('#theadd').append("<tr>"+
                        "<th>ID</th>"+
                        "<th>Name</th>"+
                        "<th>Creation Date</th>"+
                        "<th>Deadline</th>"+
                        "<th>Status</th>"+
                        "</tr>"
                    );

                    $.each(result.data, function(key, value){
                        $('#resultDiv').append("<tr>"+
                            "<td>"+value.id+"</td>"+
                            "<td>"+value.name+"</td>"+
                            "<td>"+value.createdDate+"</td>"+
                            "<td>"+value.deadline+"</td>"+
                            "<td>"+value.status+"</td>"+
                            "</tr>"
                        )
                    });
                    console.log("Success: ", result);
                }else{
                    $("#resultDiv").html("<strong>Error</strong>");
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                $("#resultDiv").html("<strong>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
})