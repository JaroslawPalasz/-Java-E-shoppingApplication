/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//przekazuje nazwe pol w ktorych znajduja sie dane
function login(username, password) {
    var xhttp = new XMLHttpRequest();
    
    //dane sa pobierane
    var arg1 = document.getElementById(username).value;
    var arg2 = document.getElementById(password).value;
    
    //generate request
    //formulowany adres - przekazywane do servletu
    xhttp.open("GET", "LoginServlet?username=" + arg1 + "&password=" + arg2, true);
    //wysylanie
    xhttp.send();
}

