export default function Member(){
    const data = getData();
    return <div>
            ${data}
    </div>
}


async function getData(){
    const res = await fetch("https://www.srookpay.co.kr");
    //const res = await fetch("https://0dev.srookpay.com/newsrp/InicisApi/IsTest");
    console.debug(res.text());
    return res;
}