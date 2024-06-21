<template>
    <div class="button-box color-red">

        <h3 class="text-center my-2">Up coming events</h3>
        <router-link to="/" class="nav-link">
            <div class="d-flex"><v-icon>mdi-calendar-alert</v-icon>
                <p>&nbsp;From 01 Apr To 30 Apr</p>
            </div>
            <div class="d-flex"><v-icon>mdi-map-marker-check</v-icon>&nbsp;<p>Lotte Hotel Yangon</p>
            </div>
        </router-link>
        <v-divider :thickness="2" class="border-opacity-50 mt-0"></v-divider>
        <router-link to="/" class="nav-link">
            <div class="d-flex"><v-icon>mdi-calendar-alert</v-icon>
                <p>&nbsp;From 01 Apr To 30 Apr</p>
            </div>
            <div class="d-flex"><v-icon>mdi-map-marker-check</v-icon>&nbsp;<p>Bangkok Projects Special Sales Event
                </p>
            </div>
        </router-link>
        <router-link class="custom-link" to="/">See all events <v-icon
                class="custom-icon">mdi-arrow-right-bold</v-icon></router-link>
    </div>
</template>


<script>
export default {

}

</script>
