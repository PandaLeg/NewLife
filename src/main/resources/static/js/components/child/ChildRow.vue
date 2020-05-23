<template>
    <div>
        <h5> {{child.nameChild}} </h5>
        <label> {{child.state}} </label>
        <br>
    </div>
</template>

<script>
    export default {
        props: ['child']
    }
</script>

<style>

</style>