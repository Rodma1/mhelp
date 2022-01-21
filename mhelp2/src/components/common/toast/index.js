import toast from "./toast"
let obj={}
obj.install =function(vue){
    //1.创建组件构造器
    const toastConstructor=vue.extend(toast);
    //2.new的方式，根据组件构造器，创建组件对象
    const toast =new toastConstructor();
    //3.将组件对象手动挂载在某一个元素上
    toast.$mount(document.createElement('div'));
    //4.toast.$el对应的就是div
    document.body.appendChild(toast.$el);


    vue.prototype.$toast=toast;
    console.log('11')
}
export default obj