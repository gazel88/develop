////interface pointlike {
////    x: number;
////    y: number;
////}

////interface named {
////    name: String;
////}

////function logPoint(point: pointlike) {
////    console.log(`x=${point.x}, y= ${point.y}`);
////}

////const obj = {
////    x: 0,
////    y: 0,
////    name: "origin"
////};

////logPoint(obj);

class Car {
    drive(name, age) {
        function test() {
            let name2 = name+"_second";
            console.log(`car's name=${name2} and age=${age}`)    

        }
        test();
    }
}

class Golfer {
    drive(name: number, age: number) {
        console.log(`golfer's name=${name} and age=${age}`)    
    }
    
}

////let w: Car = new Golfer();
////let d = w.drive('tom2', 20);

////let tt: Golfer = new Car();
////tt.drive(1, 22);


////declare function map<T, U>(f: (t: T) => U, ts: T[]): U[];
////let sns = map((n) => n.toString(), [1, 2, 3]);