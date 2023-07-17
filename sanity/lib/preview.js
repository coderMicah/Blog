//For Preview Mode
import { definePreview } from "next-sanity/preview";

const dataset = process.env.NEXT_PUBLIC_SANITY_DATASET;
const projectId = process.env.NEXT_PUBLIC_SANITY_PROJECT_ID;

function onPublicAccessOnly() {
  throw new Error("Unable to load preview as you are not logged in");
}
if (!projectId || !dataset) {
  throw new Error("Missing projectId or dataset.Chek your sanity.json or .env");
}

export const usePreview = definePreview({
  projectId,
  dataset,
  onPublicAccessOnly,
});
