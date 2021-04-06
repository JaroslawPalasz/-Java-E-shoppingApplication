/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*function getEshopping(displayTargetId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            //document.getElementById(displayTargetId).innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "CategoryServlet", true);
    xhttp.send();
}*/

function category(category) {
    var xhttp = new XMLHttpRequest();
    
    /*xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
     document.getElementById("action").innerHTML = category;
    }
  };*/
    
    //dane sa pobierane
    //var arg1 = category;
    
    var arg1 = document.getElementById(category).value;
    
    //generate request
    //formulowany adres - przekazywane do servletu
    xhttp.open("GET", "CategoryServlet?action=" + arg1, true);
    //xhttp.open("GET", arg1, true);
    //wysylanie
    xhttp.send();
}

