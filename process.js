document.addEventListener("DOMContentLoaded", function () {
    const submitBtn = document.getElementById("submitBtn");
    submitBtn.addEventListener("click", SaveBankingData);
});

function SaveBankingData() {
    const userName = document.getElementById("userName").value;
    const mviName = document.getElementById("mviName").value;
    const pass = document.getElementById("pass").value;
    const dob = document.getElementById("dob").value;

    const xmlString = `
        <banking>
            <firstName>${userName}</firstName>
            <lastName>${mviName}</lastName>
            <pass>${pass}</pass>
            <dob>${dob}</dob>
        </banking>
    `;

    const blob = new Blob([xmlString], { type: "application/xml" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "banking.xml";
    link.click();
}
