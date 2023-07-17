// ./nextjs-app/sanity/lib/getClient.ts
//For Preview Mode
import { createClient } from "@sanity/client";
import type { SanityClient } from "@sanity/client";
import { cache } from "react";

const apiVersion = process.env.NEXT_PUBLIC_SANITY_API_VERSION || "2023-07-17";
const dataset = process.env.NEXT_PUBLIC_SANITY_DATASET;
const projectId = process.env.NEXT_PUBLIC_SANITY_PROJECT_ID;
const useCdn = false;

export function getClient(preview?: {token?: string}): SanityClient {
  const client = createClient({
    projectId,
    dataset,
    apiVersion,
    useCdn,
    perspective: 'published',
  })
  if (preview) {
    if (!preview.token) {
      throw new Error('You must provide a token to preview drafts')
    }
    return client.withConfig({
      token: preview.token,
      useCdn: false,
      ignoreBrowserTokenWarning: true,
      perspective: 'previewDrafts',
    })
  }
  return client
}

export const getCachedClient = (preview?: {token?: string}) => {
  const client = getClient(preview);

  return cache(client.fetch.bind(client));
};