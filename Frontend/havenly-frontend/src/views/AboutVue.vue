<template>
    <v-container class="mt-5 pt-5">
        <div class="mt-5">
            <h1>Welcome to About Section</h1>
            <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Inventore neque odio cumque voluptatibus quos soluta expedita laboriosam dolorem dignissimos. Saepe nisi totam tenetur reiciendis pariatur quia asperiores temporibus! Soluta, similique!</p>
        </div>
    </v-container>    
</template>

<script>

    export default {
        name: "AboutVue",

        mounted() {
            localStorage.removeItem('openTab');
        },

    }

</script>

<style>

</style>