Vue.createApp({
    data(){
        return{
            data:[],
            filter:[]
        }
    },
    created(){
            axios.get('/api/ventas/todas')
            .then(response=>{
                console.log(response)
                this.data=response.data
            })
            .catch(err=>{
                console.log(err)   
            })
    },
    methods:{

    }
}).mount('#app')