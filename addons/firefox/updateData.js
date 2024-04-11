let login = "";
let prevSubs = [];
let newSubs = []
let startDate = new Date();
startDate.setDate(startDate.getDate() - startDate.getDay() - 6);
startDate.setHours(0, 0, 0, 0);
let endDate = new Date();
endDate.setDate(endDate.getDate() + 14 - endDate.getDay());
endDate.setHours(23, 59, 59, 999);

async function main() {
  const cookie = await browser.cookies.get({ url: "https://intra.epitech.eu", name: "user" });
  if (cookie) {
    const payload = cookie.value.split(".")[1];
    const decodedPayload = atob(payload);
    const data = JSON.parse(decodedPayload);
    login = data.login;
  }
  response = await fetch(`https://apicalendar.pechart.fr/activity/${login}?start=${startDate.toISOString().split('T')[0]}&end=${endDate.toISOString().split('T')[0]}`);
  data = await response.json();
  if (data === null) {
    prevSubs = [];
  } else {
    prevSubs = data;
  }
  response = await fetch(`https://intra.epitech.eu/planning/load?format=json&start=${startDate.toISOString().split('T')[0]}&end=${endDate.toISOString().split('T')[0]}`);
  data = await response.json();
  data.forEach((activity) => {
    let finalData = {
      "eventId": 0,
      "mail": "string",
      "title": "string",
      "room": "string",
      "startDate": "string",
      "endDate": "string"
    }
    let activityStart = new Date();
    let activityEnd = new Date();
    if (activity.event_registered) {
      if (!activity.rdv_group_registered && !activity.rdv_indiv_registered) {
        activityStart = new Date(activity.start);
        activityEnd = new Date(activity.end);
      } else {
        if (activity.rdv_group_registered) {
          activityStart = new Date(activity.rdv_group_registered.split('|')[0]);
          activityEnd = new Date(activity.rdv_group_registered.split('|')[1]);
        } else {
          activityStart = new Date(activity.rdv_indiv_registered.split('|')[0]);
          activityEnd = new Date(activity.rdv_indiv_registered.split('|')[1]);
        }
      }
      if (activity.room === null || !activity.room.code || activity.room.code.split('/').length === 0)
        return
      finalData.eventId = parseInt(activity.codeevent.replace("event-", ""));
      finalData.mail = login;
      finalData.title = activity.acti_title;
      finalData.room = activity.room.code.split('/').slice(-1)[0].replace("-", "").replaceAll("-", " ");
      finalData.startDate = activityStart.getTime();
      finalData.endDate = activityEnd.getTime();
      newSubs.push(finalData.eventId);
      let options = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(finalData)
      };
      fetch(`https://apicalendar.pechart.fr/activity/`, options);
    }
  });
  let noLongerSub = [];
  prevSubs.forEach((prevSub) => {
    if (!newSubs.includes(prevSub)) {
      noLongerSub.push(prevSub);
    }
  });
  noLongerSub.forEach((event) => {
    let options = {
      method: 'DELETE'
    };
    fetch(`https://apicalendar.pechart.fr/activity/${login}/${event}`, options);
  });
}

browser.tabs.onUpdated.addListener(async (tabId, changeInfo, tab) => {
  if (tab.url.includes("intra.epitech.eu") && changeInfo.status === "complete") {
    await main();
  }
});