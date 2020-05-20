<template>
    <div>
        <message-row v-for="message in messages" :key="message.id" :message="message"> </message-row>
    </div>
</template>

<script>
    import MessageRow from './MessageRow.vue'
    export default {
        components:{
            MessageRow
        },
        props: ['messages']
    }
</script>


<style>

</style>