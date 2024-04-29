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

<style>
.button-box {
    background-color: #E86F52;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 10px 24px 1px rgba(0, 0, 0, 0.42);

    .custom-link {
        color: #000;
        text-decoration: none;
        position: relative;

        .custom-icon {
            position: absolute;
            margin-top: 1px;
            transition: all 0.3s ease-in-out;
        }
    }

    .custom-link:hover .custom-icon {
        margin-left: 16px;
    }

    .v-icon {
        color: #525252;
    }
}
</style>