$(document).ready(function () {
    $('#serverNamed').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/personal/suggestions?", {q: request.term}, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );

    $("#btnServerSearch").click(function () {
        const inputText = $("#serverNamed").val();
        if (inputText.length === 0) {
            alert("Enter cpu model");
        } else {
            let serverSearch = document.getElementById('serverSearch');
            console.log(serverSearch);
            if (serverSearch) {
                let input = document.createElement("input");
                input.setAttribute("type", "hidden");
                input.setAttribute("name", "serverSearch");
                input.setAttribute("value", inputText);
                serverSearch.appendChild(input);
            }
        }
    });
});