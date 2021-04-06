/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function displayProductListTable(tableId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(tableId).innerHTML = this.responseText;
        }
    };
    
    
    xhttp.open("GET", "ProductListServlet?action=getallproducts", true);
    xhttp.send();
}

function submitForm(formId, action) {
    document.getElementById("actionDelimiter").setAttribute('value', action);
    document.getElementById(formId).submit();
}
