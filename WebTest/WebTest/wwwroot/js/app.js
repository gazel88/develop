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
var Car = /** @class */ (function () {
    function Car() {
    }
    Car.prototype.drive = function (name, age) {
        function test() {
            var name2 = name + "_second";
            console.log("car's name=".concat(name2, " and age=").concat(age));
        }
        test();
    };
    return Car;
}());
var Golfer = /** @class */ (function () {
    function Golfer() {
    }
    Golfer.prototype.drive = function (name, age) {
        console.log("golfer's name=".concat(name, " and age=").concat(age));
    };
    return Golfer;
}());
////let w: Car = new Golfer();
////let d = w.drive('tom2', 20);
////let tt: Golfer = new Car();
////tt.drive(1, 22);
////declare function map<T, U>(f: (t: T) => U, ts: T[]): U[];
////let sns = map((n) => n.toString(), [1, 2, 3]);
//# sourceMappingURL=app.js.map