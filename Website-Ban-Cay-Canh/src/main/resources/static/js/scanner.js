const scannerDiv = document.querySelector('.scanner');
const camera = scannerDiv.querySelector('h1 .fa-camera');
const stopCam = scannerDiv.querySelector('h1 .fa-circle-stop');

const form = scannerDiv.querySelector('.scanner-form');
const inputFile = form.querySelector('input');
const p = form.querySelector('p');
const img = form.querySelector('img');
const video = form.querySelector('video');
const content = form.querySelector('.content');

const closeBtn = scannerDiv.querySelector('.scanner-details .close');

const username = document.querySelector('#username');
const password = document.querySelector('#password');

form.addEventListener('click', () => inputFile.click());

inputFile.addEventListener("change", e => {
    let file = e.target.files[0];
    if (!file) return;
    fecthRequest(file);
})

function fecthRequest(file) {
    let formData = new FormData();
    formData.append('file', file);

    p.innerText = "Scanner QR Code....";

    fetch(`http://api.qrserver.com/v1/read-qr-code/`, {
        method: "POST", body: formData
    }).then(res => res.json()).then(result => {
        let text = result[0].symbol[0].data;

        if (!text)
            return p.innerText = "Couldn't Scan QR Code....";

        scannerDiv.classList.add("active");
        form.classList.add("active-img");

        img.src = URL.createObjectURL(file);
        let account_info = text.split(" ");

        username.value = account_info[0];
        password.value = account_info[1];

    })
}


let scanner;

camera.addEventListener('click', () => {
    camera.style.display = "none";
    form.classList.add("pointerEvents")
    p.innerText = "Scanning QR Code...";


    scanner = new Instascan.Scanner({ video: video });

    Instascan.Camera.getCameras()
        .then(cameras => {
            if (cameras.length > 0) {
                scanner.start(cameras[0]).then(() => {
                    form.classList.add("active-video");
                    stopCam.style.display = "inline-block";
                })
            } else {
                console.log("No camera found")
            }
        })
        .catch(function (e) {
            console.error(e);
        });

    scanner.addListener("scan", c => {
        let acco_info = c.split(" ");
        username.value = acco_info[0];
        password.value = acco_info[1];
    })
})

closeBtn.addEventListener("click", () => stopScan());
stopCam.addEventListener("click", () => stopScan());


function stopScan() {
    p.innerText = "Upload Scan QR Code....";

    camera.style.display = "inline-block";
    stopCam.style.display = "none"

    scannerDiv.classList.remove("active");
    form.classList.remove("active-video", "active-img", "pointerEvents");
    form.classList.remove("active-img");

    if (scanner) scanner.stop();
}