let trdata = document.getElementById("trdata");

const url = "http://localhost:8080/task/user/9"

function showData(tasks){
    let data = `<thead>
                        <th scope="col">ID</th>
                        <th>Descrição</th>
                        <th>Usuário</th>
                        <th>ID Usuário</th>
                    </thead>`;
    
    
    for (let task of tasks) {
     data += `<tr>
        <td col="row">${task.id}</td>
        <td>${task.descritionTask}</td>
        <td>${task.user.username}</td>
        <td>${task.user.id}</td>
    </tr>`;  

    }


    /*for (let user of users) {
     data = `<tr>
        <td col="row">${tasks.id}</td>
        <td>${tasks.username}</td>
    </tr>`;  

    //}*/

    trdata.innerHTML = data;
}

async function acessAPI(url) {
    const response = await fetch(url, {method:"GET"}) //{method="GET"}

    let data = await response.json()
    console.log(data)

    showData(data)
}
acessAPI(url);