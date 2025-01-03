Vue.createApp({
    data(){
        return{
            firstName:"",
            lastName:"",
            user:"",
            pwd:"",
            msg:'',
            loginSuccess:null,
        }
    },
    created(){

    },
    methods:{
        signIn(){
            axios.post('/api/login',`user=${this.user}&password=${this.pwd}`)
            .then(response=>{
                console.log(response)
                if(this.user.includes("admin")){
                    window.location.href = "/web/puntoventa.html"
                }else{
                    window.location.href = "/web/accounts.html"
                }
            })
            .catch(err=>{
                console.log(err)
                this.name=""
                this.pwd=""
            })
        },
        cleanMsg(){
            this.msg=""
        },
    }
}).mount('#app')